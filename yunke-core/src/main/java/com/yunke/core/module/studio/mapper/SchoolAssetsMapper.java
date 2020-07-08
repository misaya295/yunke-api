package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
import org.apache.ibatis.annotations.Param;

/**
 * 学校资产表
(SchoolAssets)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface SchoolAssetsMapper extends BaseMapper<SchoolAssets> {
    /*
     * 查找资金详细信息
     * @param page 分页对象
     * @param <T>  type
     * @return IPage<Founding>
     */
    <T> IPage<SchoolAssets> pageSchoolAssetsDetail(Page<T> page, @Param("schoolAssets") SchoolAssets schoolAssets);
}