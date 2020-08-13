package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.SchoolAssetsMapper;
import com.yunke.core.module.studio.service.ISchoolAssetsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学校资产表 (SchoolAssets)表服务实现类
 *
 * @author Pning
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SchoolAssetsServiceImpl extends
    ServiceImpl<SchoolAssetsMapper, SchoolAssets> implements
    ISchoolAssetsService {

    @Override
    public IPage<SchoolAssets> pageSchoolAssets(QueryParam param, SchoolAssets schoolAssets) {
        Page<SchoolAssets> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "id", SystemConstant.ORDER_ASC, true);
        String statrtTime = null;//查询开始时间
        String endTime = null;//查询结束时间
        if(schoolAssets.getInclusionDate()!=null&&(!"".equals(schoolAssets.getInclusionDate()))){ //funding存放的日期不为空
            String[] split_time = StrUtil.split(schoolAssets.getInclusionDate(), StrUtil.COMMA);
            if(split_time.length==2){
                statrtTime = split_time[0];
                endTime = split_time[1];
            }
        }
        return baseMapper.pageSchoolAssetsDetail(page,schoolAssets,statrtTime,endTime);
    }

    @Override
    public SchoolAssets selectSchoolAssetsById(int id) {
        return baseMapper.selectSchoolAssetsById(id);
    }

    @Override
    public void addSchoolAssets(SchoolAssets schoolAssets) {
        if (schoolAssets.getAssetsNum() != null && schoolAssets.getAssetsNum() != "") {        //添加内容中存在资产编号
            if(baseMapper.selectSchoolAssetsCountById(schoolAssets) != 0){              //该资产编号已存在
                throw new ApiException("添加的资产编号是存在的，不能重复使用");
            }
        }
        baseMapper.addSchoolAssets(schoolAssets);
    }

    @Override
    public void deleteSchoolAssetsById(int[] schoolAssetsIds) {
        baseMapper.deleteSchoolAssetsById(schoolAssetsIds);
    }

    @Override
    public void updateSchoolAssetsMessage(SchoolAssets schoolAssets) {
        baseMapper.updateSchoolAssetsMessage(schoolAssets);
    }

}