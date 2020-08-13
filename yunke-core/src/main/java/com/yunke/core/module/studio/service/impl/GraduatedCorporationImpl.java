package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.GraduatedCorporation;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.GraduatedCorporationMapper;
import com.yunke.core.module.studio.service.IGraduatedCorporation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;import cn.hutool.core.util.StrUtil;

/**
 * 毕业去向(GraduatedCorporation)表服务实现类
 *
 * @author Pning
 * @since 2020-08-10 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GraduatedCorporationImpl extends ServiceImpl<GraduatedCorporationMapper, GraduatedCorporation> implements
        IGraduatedCorporation {

    @Override
    public IPage<GraduatedCorporation> pageGraduatedCorporation(QueryParam param, GraduatedCorporation graduatedCorporation) {
        Page<Funding> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "user_id", SystemConstant.ORDER_ASC, true);
        return baseMapper.pageGraduatedCorporationDetail(page,graduatedCorporation);
    }

    @Override
    public GraduatedCorporation selectGraduatedCorporationById(int userId) {
        return baseMapper.selectGraduatedCorporationByUserId(userId);
    }

    @Override
    public void updateGraduatedCorporationMessage(GraduatedCorporation graduatedCorporation) {
        baseMapper.updateGraduatedCorporationMessage(graduatedCorporation);
    }

    @Override
    public void addGraduatedCorporation(GraduatedCorporation graduatedCorporation) {
        baseMapper.addGraduatedCorporation(graduatedCorporation);
    }

    @Override
    public void deleteGraduatedCorporationByUserIds(int[] userIds) {
        baseMapper.deleteGraduatedCorporationByUserIds(userIds);
    }
}
