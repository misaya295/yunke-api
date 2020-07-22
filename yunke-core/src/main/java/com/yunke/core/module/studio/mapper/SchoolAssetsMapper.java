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
    /**
     * 查找资金详细信息
     * @param page 分页对象
     * @param <T>  type
     * @return <T> IPage<SchoolAssets>
     */
    <T> IPage<SchoolAssets> pageSchoolAssetsDetail(Page<T> page, @Param("schoolAssets") SchoolAssets schoolAssets);

    /**
     * 通过资产id查询该资产的详细信息
     * @param id 资产id
     * @return SchoolAssets
     */
    SchoolAssets selectSchoolAssetsById(@Param("schoolAssetsId")int id);
    /**
     * 添加学校资产
     * @param schoolAssets 学校资产
     */
    void addSchoolAssets(@Param("schoolAssets")SchoolAssets schoolAssets);

    /**
     * 根据提供的id删除学校资产数据
     * @param schoolAssetsIds 学校资产id数组
     */
    void deleteSchoolAssetsById(int[] schoolAssetsIds);

    /**
     * 根据提供的id修改学校资产数据
     * @param schoolAssets 学校资产
     */
    void updateSchoolAssetsMessage(@Param("schoolAssets")SchoolAssets schoolAssets);

    /**
     * 根据学校资产的id或者编号判断这个资产是否存在，存在返回1，不存在返回0，其他为有问题
     * @param schoolAssets 学校资产
     */
    int selectSchoolAssetsCountById(@Param("schoolAssets")SchoolAssets schoolAssets);
}
