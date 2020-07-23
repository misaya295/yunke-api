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
        }else if(split_schoolAssetsRepairIds.length == 0){
            throw new ApiException("前端传入维修申请id为空，删除失败");
        }
    }

    /**
     *  请求类型：post
     *  @param schoolAssetsRepair 学校资产对象
     *  作用：添加维修申请
     */
    @PostMapping("/{schoolAssetsRepair}")
    @ControllerEndpoint(operation = "添加学校资产维修申请成功", exceptionMessage = "添加校资产维修申请失败")
    public void addSchoolAssetsRepairs(@PathVariable("schoolAssetsRepair") SchoolAssetsRepair schoolAssetsRepair) {
        if(schoolAssetsRepair!=null){
            schoolAssetsRepairService.addSchoolAssetsRepair(schoolAssetsRepair);
        }else{
            throw new ApiException("添加的维修申请数据不能为空");
        }
    }

    /**
     *  请求类型：Put
     *  @param schoolAssetsRepair 学校资产对象
     *  作用：根据经费id修改资产数据
     */
    @PutMapping("/{schoolAssetsRepair}")
    @ControllerEndpoint(operation = "修改校资产维修申请数据成功", exceptionMessage = "修改校资产维修申请数据")
    public void updateSchoolAssetsRepairs(@PathVariable("schoolAssetsRepair") SchoolAssetsRepair schoolAssetsRepair) {
        if(schoolAssetsRepair!=null){
            schoolAssetsRepairService.updateSchoolAssetsRepairsMessage(schoolAssetsRepair);
        }else{
            throw new ApiException("不能把资产维修申请的数据改为空");
        }

    }
}