package com.yunke.core.module.studio.service.impl;

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
 * @author chachae
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
        return baseMapper.pageSchoolAssetsDetail(page,schoolAssets);
    }

    @Override
    public SchoolAssets selectSchoolAssetsById(int id) {
        return baseMapper.selectSchoolAssetsById(id);
    }

    @Override
    public void addSchoolAssets(SchoolAssets schoolAssets) {
        Boolean inserted = true;                                                        //判断是否符合添加条件：1.必填内容不为空，2.资产编号不重复
        if(schoolAssets.getAssetsName()==""|| schoolAssets.getAssetsName()==null) {     //必填内容不为空
           inserted = false;
        }else {
            throw new ApiException("添加资产的名字不能为空");
        }
        if(schoolAssets.getAssetsNum()!=null&&schoolAssets.getAssetsNum()!="") {        //添加内容中存在资产编号
            if(baseMapper.selectSchoolAssetsCountById(schoolAssets) != 0){              //该资产编号已存在
                inserted = false;
            }else {
                throw new ApiException("添加的资产编号是存在的，不能重复使用");
            }
        }else {
            throw new ApiException("添加资产的编号不能为空");
        }

        if(inserted){
            baseMapper.addSchoolAssets(schoolAssets);
        }else{
            throw new ApiException("添加的支持数据缺失导致添加失败");
        }
    }

    @Override
    public void deleteSchoolAssetsById(int[] schoolAssetsIds) {
        baseMapper.deleteSchoolAssetsById(schoolAssetsIds);
    }

    @Override
    public void updateSchoolAssetsMessage(SchoolAssets schoolAssets) {
        Boolean updated = true;                                                        //判断是否符合添加条件：1.必填内容不为空，2.资产编号不重复
        if(schoolAssets.getAssetsName()==""|| schoolAssets.getAssetsName()==null) {     //必填内容不为空
            if(schoolAssets.getAssetsNum()!=null&&schoolAssets.getAssetsNum()!="") {
                baseMapper.updateSchoolAssetsMessage(schoolAssets);
            }else {
                throw new ApiException("资产的编号不能改为空");
            }
        }else {
            throw new ApiException("资产的名字不能改为空");
        }
    }

}