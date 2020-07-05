package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Funding;
import org.apache.ibatis.annotations.Param;

/**
 * 经费表(Funding)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface FundingMapper extends BaseMapper<Funding> {
    /*
     * 查找资金详细信息
     * @param page 分页对象
     * @param <T>  type
     * @return IPage<Founding>
   */
    <T> IPage<Funding> pageFundingDetail(Page<T> page, @Param("funding") Funding funding);

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
     * 通过id修改指定经费数据
     * @param fundingIds 资金id
     */
    void updateFunding(@Param("funding") Funding funding);
}