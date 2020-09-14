package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.entity.studio.SchoolAssetsRepair;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.ISchoolAssetsRepairService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 维修信息表 (SchoolAssetsRepair)表控制层
 *
 * @author Pning
 * @since 2020-07-10 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/school/assets/repair")
public class SchoolAssetsRepairController {
    private final ISchoolAssetsRepairService schoolAssetsRepairService;
    /*
     * 请求类型：GET
     * @param param 页数
     * @param schoolAssetsRepair 模糊查询的条件
     * 作用：根据页数param.pageNum查询满足条件的前10条的资产数据
     */
    @GetMapping
    public R<Map<String, Object>> SchoolAssetsRepairListBypage(QueryParam param, SchoolAssetsRepair schoolAssetsRepair) {
        IPage<SchoolAssetsRepair> result = schoolAssetsRepairService.pageSchoolAssetsRepair(param,schoolAssetsRepair);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 请求类型：GET
     * @param schoolAssetsRepairId 维修申请id
     */
    @GetMapping("/{schoolAssetsRepairId}")
    public R<SchoolAssetsRepair> selectSchoolAssetsRepairById(@PathVariable("schoolAssetsRepairId") int schoolAssetsRepairId) {
        SchoolAssetsRepair schoolAssetsRepair = schoolAssetsRepairService.selectSchoolAssetsRepairIdById(schoolAssetsRepairId);
        if(schoolAssetsRepair!=null){
            return R.ok(schoolAssetsRepair);
        }else{
            throw new ApiException("查询不到这个维修申请");
        }
    }


    /**
     *  请求类型：Delete
     *  @param schoolAssetsRepairIds 维修申请id
     *  作用：根据维修申请id删除维修申请数据
     */
    @DeleteMapping("/{schoolAssetsRepairIds}")
    @ControllerEndpoint(operation = "删除该维修申请数据", exceptionMessage = "删除该维修申请数据失败")
    public void deleteSchoolAssetsRepairs(@PathVariable("schoolAssetsRepairIds") String schoolAssetsRepairIds) {
        int[] split_schoolAssetsRepairIds = StrUtil.splitToInt(schoolAssetsRepairIds, StrUtil.COMMA);
        if(split_schoolAssetsRepairIds.length>0){
            schoolAssetsRepairService.deleteSchoolAssetsRepairById(split_schoolAssetsRepairIds);
        }
    }

    /**
     *  请求类型：post
     *  @param schoolAssetsRepair 学校资产对象
     *  作用：添加维修申请
     */
    @PostMapping
    @ControllerEndpoint(operation = "添加学校资产维修申请成功", exceptionMessage = "添加校资产维修申请失败")
    public void addSchoolAssetsRepairs(@Valid SchoolAssetsRepair schoolAssetsRepair) {
        if(schoolAssetsRepair.getAssetsName() != null) {
            schoolAssetsRepairService.addSchoolAssetsRepair(schoolAssetsRepair);
        }else{
            throw new ApiException("添加的维修申请的资产id不能为空");
        }
    }

    /**
     *  请求类型：Put
     *  @param schoolAssetsRepair 学校资产对象
     *  作用：根据经费id修改资产数据
     */
    @PutMapping
    @ControllerEndpoint(operation = "修改校资产维修申请数据成功", exceptionMessage = "修改校资产维修申请数据失败")
    public void updateSchoolAssetsRepairsMessage(@Valid SchoolAssetsRepair schoolAssetsRepair) {
        if(schoolAssetsRepair.getId() != null) {
            schoolAssetsRepairService.updateSchoolAssetsRepairsMessage(schoolAssetsRepair);
        }else{
            throw new ApiException("修改的维修申请id不能为空");
        }
    }
    /**
     *  请求类型：Put
     *  @param schoolAssetsRepair 学校资产对象
     *  作用：根据经费id修改资产数据
     */
    @PutMapping("/state")
    @ControllerEndpoint(operation = "修改校资产维修状态成功", exceptionMessage = "修改校资产维修状态失败")
    public void updateSchoolAssetsRepairsState(@Valid SchoolAssetsRepair schoolAssetsRepair) {
        if(schoolAssetsRepair.getId() != null) {
            if (schoolAssetsRepair.getState() >= 0 && schoolAssetsRepair.getState() <= 4) {
                schoolAssetsRepairService.updateSchoolAssetsRepairsState(schoolAssetsRepair);
            } else {
                throw new ApiException("经费申请的状态修改值不在正常范围");
            }
        }else{
            throw new ApiException("修改的维修状态的id不能为空");
        }
    }
}