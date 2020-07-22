package com.yunke.core.module.studio.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Postgraduate;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.PostgraduateMapper;
import com.yunke.core.module.studio.service.IPostgraduateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 考研人员表(Postgraduate)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PostgraduateServiceImpl extends
    ServiceImpl<PostgraduateMapper, Postgraduate> implements
    IPostgraduateService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPostgraduate(Postgraduate postgraduate) {
        if(this.getOne(new LambdaQueryWrapper<Postgraduate>().eq(Postgraduate::getUserId,postgraduate.getUserId()).eq(Postgraduate::getState,0))!=null){
            throw new ApiException("你已经有正在进行的考研任务，无法再添加");
        }else {
            this.save(postgraduate);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePostgraduate(Postgraduate postgraduate) {
            this.updateById(postgraduate);
    }

    @Override
    public IPage<Postgraduate> postgraduateList(QueryParam param, Postgraduate postgraduate) {
        Page<Postgraduate> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "id", SystemConstant.ORDER_ASC, true);
        return baseMapper.pagePostgraduate(page,postgraduate);
    }

    @Override
    public Map getPostgraduate(String id) {
        return baseMapper.getPostgraduate(id);
    }
}