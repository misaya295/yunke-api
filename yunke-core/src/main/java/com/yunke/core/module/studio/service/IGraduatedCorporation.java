package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.GraduatedCorporation;

/**
 * 毕业去向表(GraduatedCorporation)表服务接口
 *
 * @author Pning
 * @since 2020-08-10 14:04:56
 */
public interface IGraduatedCorporation extends IService<GraduatedCorporation> {
    /**
     * 分页查询毕业去向数据
     * @param graduatedCorporation 查询对象
     * @param param 分页参数
     * @return IPage<GraduatedCorporation> 含毕业去向的分页对象
     */
    IPage<GraduatedCorporation> pageGraduatedCorporation(QueryParam param, GraduatedCorporation graduatedCorporation);

    /**
     * 通过用户userId返回该用户的毕业去向数据
     * @param userId 经费id
     * @return 含毕业去向的分页对象
     */
    GraduatedCorporation selectGraduatedCorporationById(int userId);
    /**
     * 修改毕业去向数据
     * @param graduatedCorporation 毕业去向对象
     */
    void updateGraduatedCorporationMessage(GraduatedCorporation graduatedCorporation);


    /**
     * 添加毕业去向数据
     * @param graduatedCorporation 毕业去向对象
     */
    void addGraduatedCorporation(GraduatedCorporation graduatedCorporation);

    /**
     * 根据提供的用户id删除毕业去向数据
     * @param userIds 用户id数组
     */
    void deleteGraduatedCorporationByUserIds(int[] userIds);

}

