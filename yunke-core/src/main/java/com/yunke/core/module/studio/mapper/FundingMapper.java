package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.system.SystemUser;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 经费表(Funding)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface FundingMapper extends BaseMapper<Funding> {
    /**
     * 查找资金详细信息
     * @param page 分页对象
     * @param <T>  type
     * @param statrtTime 开始时间
     * @param endTime     结束时间
     * @return IPage<Founding>
   */
    <T> IPage<Funding> pageFundingDetail(Page<T> page, @Param("funding") Funding funding,@Param("statrtTime") String statrtTime,@Param("endTime")String endTime);

    /*
     * 查找满足条件的经费的总个数
     * @param page 分页对象
     * @return int 满足条件的总个数
     */
    int pageFundingCount(@Param("funding") Funding funding);

    /*
     * 通过id判断这个id是否存在
     * @param fundingId
     * @return int 存在该id的经费条数，返回1则存在，0则不存在，返回其他则数据有问题
     */
    int selectFundingCountById(@Param("fundingId")int fundingId);


    /*
     * 通过id查询该id的经费数据
     * @param fundingId
     * @return Funding对象
     */
    Funding selectFundingById(@Param("fundingId")int fundingId);

    /*
     * 通过id删除指定经费数据
     * @param fundingIds 资金id
     */
    void deleteByFundingid(int[] fundingIds);

    /*
     * 修改指定经费数据，但是无法修改申请状态
     * @param funding 经费对象
     */
    void updateFundingMessage(@Param("funding") Funding funding);

    /*
     * 修改指定经费申请转态，但无法修改申请数据
     * @param funding 经费对象
     */
    void updateFundingState(@Param("funding") Funding funding);

    /*
     * 通过该角色id下的用户，只返回用户id和真实姓名
     * @param roleId 角色id数组
     */
    List<SystemUser> selectUserNameByRoleId (int[] roleId);

    /*
     * 添加经费申请
     * @param funding 经费对象，里面的name，apply_time,proposer_id不能为空
     */
    void addFunding(@Param("funding") Funding funding);

    /**
     * 查询时间范围内的花费/入账/剩余资金
     * @param statrtTime 开始时间
     * @param endTime    结束时间
     * @param kind      查询类型:-1/0/1,分别对应:开销/剩余/入账
     */
    double queryFundingCostByTime(@Param("statrtTime") String statrtTime,@Param("endTime")String endTime,@Param("kind")String kind);
}