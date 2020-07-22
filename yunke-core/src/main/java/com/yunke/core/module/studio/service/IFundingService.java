package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.system.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 经费表(Funding)表服务接口
 *
 * @author Pning
 * @since 2020-07-13 14:04:56
 */
public interface IFundingService extends IService<Funding> {
    /**
     * 分页查询经费数据
     * @param funding 查询对象
     * @param param 分页参数
     * @return 分页对象
     */
    IPage<Funding> pageFunding(QueryParam param, Funding funding);

    /**
     * 满足查询条件的经费申请个数
     * @param funding 查询对象
     * @return int 满足查询条件的个数
     */
    int pageFundingCount(Funding funding);

    /**
     * 根据提供的id删除转态为申请失败的经费数据
     * @param fundingIds 经费id数组
     */
    void deleteFundings(int[] fundingIds);

    /**
     * 修改经费数据
     * @param funding 经费对象
     */
    void updateFundingMessage(Funding funding);

    /**
     * 通过经费id返回该经费数据
     * @param fundingId 经费id
     */
    Funding selectFundingById(int fundingId);

    /*
     * 通过该角色id下的用户，只返回用户id和真实姓名
     * @param roleId 角色id数组
     */
    List<SystemUser> selectUserNameByRoleId (int[] roleId);

    /*
     * 添加经费申请
     * @param funding 经费对象，里面的name,apply_time，proposer_id不能为空
     */
    void addFunding(Funding funding);

    /*
     * 修改指定经费申请转态，但无法修改申请数据
     * @param funding 经费对象
     */
    void updateFundingState(Funding funding);

}