package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.FundingMapper;
import com.yunke.core.module.studio.service.IFundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 经费表(Funding)表服务实现类
 *
 * @author Pning
 * @since 2020-07-14 14:04:56
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
        String statrtTime = null;//查询开始时间
        String endTime = null;//查询结束时间
        if(funding.getSuccessTime()!=null&&(!"".equals(funding.getSuccessTime()))){ //funding存放的日期不为空
            String[] split_time = StrUtil.split(funding.getSuccessTime(), StrUtil.COMMA);
            if(split_time.length==2){
                statrtTime = split_time[0];
                endTime = split_time[1];
            }
        }
        return baseMapper.pageFundingDetail(page, funding,statrtTime,endTime);
    }

    @Override
    public void deleteFundingByFundingids(int[] fundingIds) {
        baseMapper.deleteFundingByFundingids(fundingIds);// 删除这个基金数据
    }

    @Override
    public void updateFundingMessage(Funding funding) {
        if (baseMapper.selectFundingCountById(funding.getId()) == 1) {       //判断这个这个id的数据是否存在
            baseMapper.updateFundingMessage(funding);                      //修改这个经费对象的数据
        }else{
            throw new ApiException("所要需改的经费数据不存在");
        }
    }

    @Override
    public Funding selectFundingById(int fundingId) {
        return baseMapper.selectFundingById(fundingId);
    }

    @Override
    public List<SystemUser> selectUserNameByRoleId(int[] roleId) {
        return baseMapper.selectUserNameByRoleId(roleId);
    }

    @Override
    public void addFunding(Funding funding) {
        baseMapper.addFunding(funding);
    }

    @Override
    public void updateFundingState(Funding funding) {
        baseMapper.updateFundingState(funding);
    }

    @Override
    public double queryFundingCostByTime(String statrtTime, String endTime, String kind) {
        if(kind.equals("1")||kind.equals("0")||kind.equals("-1")) {
            return baseMapper.queryFundingCostByTime(statrtTime, endTime, kind);
        }else{
            throw new ApiException("经费账单查询异常");
        }
    }


}