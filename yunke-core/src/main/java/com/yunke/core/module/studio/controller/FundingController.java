package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IFundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 经费表(Funding)表控制层
 *
 * @author Pning
 * @since 2020-07-13 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/funding")
public class FundingController {
    private final IFundingService fundingService;

    /*
     * 请求类型：GET
     * @param param 页数
     * @param funding 模糊查询的条件
     * 作用：根据页数param.pageNum查询10条的经费数据
     */
    @GetMapping
    @PreAuthorize("hasAuthority('user:view')")
    public R<Map<String, Object>> FundingListBypage(QueryParam param,Funding funding) {
        IPage<Funding> result = fundingService.pageFunding(param,funding);
        System.out.println(result.toString());
        return R.ok(PageUtil.toPage(result));
    }

    /*
     *   请求类型：GET
     *  @param fundingIds 经费id
     *  作用：根据经费id删除数据
     */
    @DeleteMapping("/delete")
    @ControllerEndpoint(operation = "删除该经费数据", exceptionMessage = "删除该经费数据失败")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable int[] fundingIds) {
         fundingService.deleteFundings(fundingIds);
    }
}