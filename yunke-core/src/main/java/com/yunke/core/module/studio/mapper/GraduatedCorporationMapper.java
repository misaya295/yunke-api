package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.GraduatedCorporation;
import org.apache.ibatis.annotations.Param;

/**
 * 毕业去向表(GraduatedCorporation)表数据库访问层
 *
 * @author Pning
 * @since 2020-08-10 14:04:56
 */
public interface GraduatedCorporationMapper extends BaseMapper<GraduatedCorporation> {
    /**
     * 查找资金详细信息
     * @param page 分页对象
     * @param <T>  type
     * @param graduatedCorporation 毕业去向对象
     * @return IPage<GraduatedCorporation>
     */
    <T> IPage<GraduatedCorporation> pageGraduatedCorporationDetail(Page<T> page, @Param("graduatedCorporation") GraduatedCorporation graduatedCorporation);

    /**
     * 通过用户userId返回该用户的去向公司数目
     * @param userId 经费id
     * @return int
     */
    int selectGraduatedCorporationCountByUserId(@Param("userId")long userId);

    /**
     * 通过用户userId返回该用户的毕业去向数据
     * @param userId 经费id
     * @return GraduatedCorporation
     */
    GraduatedCorporation selectGraduatedCorporationByUserId(@Param("userId")long userId);

    /**
     * 修改毕业去向数据
     * @param graduatedCorporation 毕业去向对象
     */
    void updateGraduatedCorporationMessage(@Param("graduatedCorporation")GraduatedCorporation graduatedCorporation);

    /**
     * 添加毕业去向数据
     * @param graduatedCorporation 毕业去向对象
     */
    void addGraduatedCorporation(@Param("graduatedCorporation")GraduatedCorporation graduatedCorporation);

    /**
     * 根据提供的用户id删除毕业去向数据
     * @param userIds 用户id数组
     */
    void deleteGraduatedCorporationByUserIds(int[] userIds);
}
