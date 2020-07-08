package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;

/**
 * 学校资产表
(SchoolAssets)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface ISchoolAssetsService extends IService<SchoolAssets> {
    /**
     * 分页查询经费数据
     * @param schoolAssets 查询对象
     * @param param 分页参数
     * @return 分页对象
     */
    IPage<SchoolAssets> pageSchoolAssets(QueryParam param, SchoolAssets schoolAssets);
}