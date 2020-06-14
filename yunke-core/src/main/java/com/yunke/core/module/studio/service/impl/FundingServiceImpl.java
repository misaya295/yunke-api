package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.core.module.studio.mapper.FundingMapper;
import com.yunke.core.module.studio.service.IFundingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 经费表(Funding)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FundingServiceImpl extends ServiceImpl<FundingMapper, Funding> implements
    IFundingService {

}