package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.entity.studio.SchoolAssetsRepair;

/**
 * 维修信息表
(SchoolAssetsRepair)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface ISchoolAssetsRepairService extends IService<SchoolAssetsRepair> {
    /**
     * 分页查询学校资产维修申请数据
     * @param schoolAssetsRepair 查询资产维修申请对象
     * @param param 分页参数
     * @return 分页对象
     */
    IPage<SchoolAssetsRepair> pageSchoolAssetsRepair(QueryParam param, SchoolAssetsRepair schoolAssetsRepair);

    /**
     * 根据提供的id删除资产维修申请数据
     * @param schoolAssetsRepairIds 学校资产维修申请id数组
     */
    void deleteSchoolAssetsRepairById(int[] schoolAssetsRepairIds);


    /**
     * 添加学校维修资产申请
     * @param schoolAssetsRepair 维修申请对象
     */
    void addSchoolAssetsRepair(SchoolAssetsRepair schoolAssetsRepair);

    /**
     * 根据提供的id修改学校资产维修申请数据
     * @param schoolAssetsRepair 维修申请对象
     */
    void updateSchoolAssetsRepairsMessage(SchoolAssetsRepair schoolAssetsRepair);

}