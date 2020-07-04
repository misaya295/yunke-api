package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.FundingMapper;
import com.yunke.core.module.studio.service.IFundingService;
import com.yunke.core.module.system.service.IUserDataPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 经费表(Funding)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FundingServiceImpl extends ServiceImpl<FundingMapper, Funding> implements
        IFundingService {

    @Override
    public IPage<Funding> pageFunding(QueryParam param, Funding funding) {
        Page<Funding> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "id", SystemConstant.ORDER_ASC, true);
        return baseMapper.pageFundingDetail(page, funding);
    }

    @Override
    public void deleteFundings(int[] fundingIds) {
        // 删除这个基金数据
        baseMapper.deleteByFundingid(fundingIds);
    }
}