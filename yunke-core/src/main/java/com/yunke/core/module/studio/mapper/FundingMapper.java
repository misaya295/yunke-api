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
     * 通过id删除指定资金
     * @param fundingIds 资金id
     */
    void deleteByFundingid(int[] fundingIds);
}