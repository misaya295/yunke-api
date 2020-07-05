package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;

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
    void updateFunding(Funding funding);

    /**
     * 通过经费id返回该经费数据
     * @param fundingId 经费id
     */
    Funding selectFundingById(int fundingId);
}