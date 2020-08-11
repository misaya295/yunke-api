package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.common.core.entity.studio.Items;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.generator.IdGenerateUtil;
import com.yunke.core.module.studio.generator.TaskTypeConstant;
import com.yunke.core.module.studio.mapper.ItemsMapper;
import com.yunke.core.module.studio.service.IItemsService;
import com.yunke.core.module.studio.service.IMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 项目_任务表(Items)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

    private final IMembersService membersService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTask(Items items, String[] userId, String[] state) {
        //生成带任务类型的id
        items.setItemsId(IdGenerateUtil.nextId(TaskTypeConstant.ITEMS));
        //保存任务
        this.save(items);
        //添加成员
        ArrayList<Members> members = new ArrayList<>(userId.length);
        IntStream.range(0, userId.length).forEach(index -> {
            members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), items.getItemsId()));
        });
        this.membersService.saveBatch(members);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(String[] ids) {
        //删除任务
        //先判断项目是否被软件著作权绑定
        Stream.of(ids).forEach( id ->
                {
                    if (baseMapper.getCopyright(id)!=null) {
                        throw new ApiException("项目已经与软件著作权关联，请先删除相关软件著作权任务");
                    } else {
                        this.removeById(id);
                    }
                }
        );
        //删除成员
        Stream.of(ids).forEach(id -> this.membersService.remove(new LambdaQueryWrapper<Members>().eq(Members::getTaskId, id)));


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(Items items) {
        //进行中的任务
        if (items.getState() == 1||(items.getReimbursement()!=null&&items.getReimbursement()==1)) {

            this.updateById(items); //修改任务
            if(items.getUserId()!=null&&items.getUserId()!="") {
                //修改成员
                //1,先删除原先成员列表
                this.membersService.remove(new LambdaQueryWrapper<Members>().eq(Members::getTaskId, items.getItemsId()));
                //添加成员
                String[] userId = items.getUserId().split(StrUtil.COMMA);
                String[] state = items.getM_state().split(StrUtil.COMMA);
                ArrayList<Members> members = new ArrayList<>(userId.length);
                IntStream.range(0, userId.length).forEach(index -> {
                    members.add(new Members(Integer.parseInt(userId[index]), Integer.parseInt(state[index]), items.getItemsId()));
                });
                this.membersService.saveBatch(members);
            }
        }

    }

    @Override
    public IPage<Items> pageTaskList(QueryParam param, Items items) {
        Page<Items> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "title", SystemConstant.ORDER_ASC, true);
        return baseMapper.pageTask(page,items);
    }

    @Override
    public Map<String, Object> getTask(String itemsId) {
        return baseMapper.getTask(itemsId);
    }

    @Override
    public Integer getAllTaskCount() {
        return this.count();
    }

}