package com.yunke.core.module.studio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.ISchoolAssetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;


/**
 * 学校资产表 (SchoolAssets)表控制层
 *
 * @author Pning
 * @since 2020-07-13 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/school/assets")
public class SchoolAssetsController {
    private final ISchoolAssetsService schoolAssetsService;
    /**
     * 请求类型：GET
     * @param param 页数
     * @param schoolAssets 模糊查询的条件
     * 作用：根据页数param.pageNum查询满足条件的前10条的资产数据
     */
    @GetMapping
    public R<Map<String, Object>> SchoolAssetsListBypage(QueryParam param, SchoolAssets schoolAssets) {
        IPage<SchoolAssets> result = schoolAssetsService.pageSchoolAssets(param,schoolAssets);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 请求类型：GET
     * @param id 资产id
     * 作用：根据资产id查询该ID的数据，提供给前端修改
     */
    @GetMapping("/selectSchoolAssetsById")
    public R<SchoolAssets> selectSchoolAssetsById(int id) {
        SchoolAssets schoolAssets = schoolAssetsService.selectSchoolAssetsById(id);
        return R.ok(schoolAssets);
    }
    /**
     *  请求类型：post
     *  @param schoolAssets 学校资产对象
     *  作用：添加学校资产，SchoolAssets对象的name,proposer_id和apply_time不能为空，proposer_id为当前登录的用户user_id
     */
    @PostMapping("/addSchoolAssets")
    @ControllerEndpoint(operation = "添加学校资产成功", exceptionMessage = "添加学校资产失败")
    public void addSchoolAssets(SchoolAssets schoolAssets) {
        schoolAssetsService.addSchoolAssets(schoolAssets);
    }

    /**
     *  请求类型：Delete
     *  @param schoolAssetsIds 资产id
     *  作用：根据经费id删除数据
     */
    @DeleteMapping("/deleteSchoolAssets")
    @ControllerEndpoint(operation = "删除该学校资产数据", exceptionMessage = "删除该学校资产数据失败")
    public void deleteSchoolAssets(@NotBlank(message = "{required}") @PathVariable int[] schoolAssetsIds) {
        schoolAssetsService.deleteSchoolAssetsById(schoolAssetsIds);
    }

    /**
     *  请求类型：Put
     *  @param schoolAssets 资产对象
     *  作用：根据经费id修改资产数据
     */
    @PutMapping("/updateSchoolAssets")
    @ControllerEndpoint(operation = "修改该该学校资产数据", exceptionMessage = "修改该学校资产数据")
    public void updateSchoolAssets(@Valid SchoolAssets schoolAssets) {
        schoolAssetsService.updateSchoolAssetsMessage(schoolAssets);
    }

}