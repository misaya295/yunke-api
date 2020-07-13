package com.yunke.core.module.studio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.entity.studio.SchoolAssetsRepair;
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
     * @param funding 模糊查询的条件
     * 作用：根据页数param.pageNum查询满足条件的前10条的资产数据
     */
    @GetMapping
    public R<Map<String, Object>> FundingListBypage(QueryParam param, SchoolAssetsRepair schoolAssetsRepair) {
        IPage<SchoolAssetsRepair> result = schoolAssetsRepairService.pageSchoolAssetsRepair(param,schoolAssetsRepair);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     *  请求类型：Delete
     *  @param schoolAssetsRepairIds 维修申请id
     *  作用：根据经费id删除数据
     */
    @DeleteMapping("/deleteSchoolAssetsRepair")
    @ControllerEndpoint(operation = "删除该维修申请数据", exceptionMessage = "删除该维修申请数据失败")
    public void deleteSchoolAssetsRepairs(@NotBlank(message = "{required}") @PathVariable int[] schoolAssetsRepairIds) {
        schoolAssetsRepairService.deleteSchoolAssetsRepairById(schoolAssetsRepairIds);
    }

//下面接口缺少文件上传下载
    /**
     *  请求类型：post
     *  @param schoolAssetsRepair 学校资产对象
     *  作用：添加学校资产，SchoolAssets对象的name,proposer_id和apply_time不能为空，proposer_id为当前登录的用户user_id
     */
    @PostMapping("/addSchoolAssetsRepair")
    @ControllerEndpoint(operation = "添加学校资产维修申请成功", exceptionMessage = "添加校资产维修申请失败")
    public void addSchoolAssetsRepairs(SchoolAssetsRepair schoolAssetsRepair) {
        schoolAssetsRepairService.addSchoolAssetsRepair(schoolAssetsRepair);
    }

    /**
     *  请求类型：Put
     *  @param schoolAssetsRepair 学校资产对象
     *  作用：根据经费id修改资产数据
     */
    @PutMapping("/updateSchoolAssetsRepair")
    @ControllerEndpoint(operation = "修改校资产维修申请数据成功", exceptionMessage = "修改校资产维修申请数据")
    public void updateSchoolAssetsRepairs(@Valid SchoolAssetsRepair schoolAssetsRepair) {
        schoolAssetsRepairService.updateSchoolAssetsRepairsMessage(schoolAssetsRepair);
    }
}