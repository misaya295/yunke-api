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
}