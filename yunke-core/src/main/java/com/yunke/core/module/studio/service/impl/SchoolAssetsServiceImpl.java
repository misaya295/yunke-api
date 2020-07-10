package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
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
        System.out.println( baseMapper.pageSchoolAssetsDetail(page,schoolAssets));
        return baseMapper.pageSchoolAssetsDetail(page,schoolAssets);
    }

    @Override
    public void addSchoolAssets(SchoolAssets schoolAssets) {
        Boolean inserted = true;                                                        //判断是否符合添加条件：1.必填内容不为空，2.资产编号不重复
        if(schoolAssets.getAssetsName()==""|| schoolAssets.getAssetsName()==null) {     //必填内容不为空
           inserted = false;
        }
        if(schoolAssets.getAssetsNum()!=null&&schoolAssets.getAssetsNum()!="") {        //添加内容中存在资产编号
            if(baseMapper.selectSchoolAssetsCountById(schoolAssets) != 0){              //该资产编号已存在
                inserted = false;
            }
        }
        if(inserted){
            baseMapper.addSchoolAssets(schoolAssets);
        }
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