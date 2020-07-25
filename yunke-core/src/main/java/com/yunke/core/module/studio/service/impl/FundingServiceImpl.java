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
import com.yunke.core.module.system.service.IUserDataPermissionService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
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
    public void deleteFundings(int[] fundingIds) {
        baseMapper.deleteByFundingid(fundingIds);// 删除这个基金数据
    }

    @Override
    public void updateFundingMessage(Funding funding) {
        if(funding.getId()!=null) {
            if (baseMapper.selectFundingCountById(funding.getId()) == 1) {       //判断这个这个id的数据是否存在
                baseMapper.updateFundingMessage(funding);                      //修改这个经费对象的数据
            }else{
                throw new ApiException("所要需改的经费数据不存在");
            }
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
        //只有申请人,申请时间和申请事件名称都有的情况下才可以提交申请
        if(funding.getName()!=""&&funding.getName()!=null && funding.getProposerId()!=0&&funding.getProposerId()!=null&&funding.getApplyTime()!=null&&funding.getApplyTime()!=""){
            baseMapper.addFunding(funding);
        }else{
            throw new ApiException("添加的经费申请里必填数据不能为空");
        }
    }

    @Override
    public void updateFundingState(Funding funding) {
        if(funding.getState()>=1 &&funding.getState()<=4) {
            baseMapper.updateFundingState(funding);
        }else{
            throw new ApiException("经费申请的状态修改值不在正常范围");
        }
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