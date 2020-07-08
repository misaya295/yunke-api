package com.yunke.core.module.studio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.module.studio.service.ISchoolAssetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    /*
     * 请求类型：GET
     * @param param 页数
     * @param funding 模糊查询的条件
     * 作用：根据页数param.pageNum查询满足条件的前10条的资产数据
     */
    @GetMapping
    public R<Map<String, Object>> FundingListBypage(QueryParam param, SchoolAssets schoolAssets) {
        IPage<SchoolAssets> result = schoolAssetsService.pageSchoolAssets(param,schoolAssets);
        return R.ok(PageUtil.toPage(result));
    }
}