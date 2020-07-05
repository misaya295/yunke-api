package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IFundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * 作用：根据页数param.pageNum查询满足条件的前10条的经费数据
     */
    @GetMapping
    public R<Map<String, Object>> FundingListBypage(QueryParam param,Funding funding) {
        funding.setName("1");
        IPage<Funding> result = fundingService.pageFunding(param,funding);
        //int count = fundingService.pageFundingCount(funding);//符合该条件的个数,page自己封装好了无需自己写，这里先留着，看后面会不会用到类似的,删了怪可惜的
        return R.ok(PageUtil.toPage(result));
    }

    /*
     *  请求类型：Delete
     *  @param fundingIds 经费id
     *  作用：根据经费id删除数据
     */
    @DeleteMapping("/deleteFundings")
    @ControllerEndpoint(operation = "删除该经费数据", exceptionMessage = "删除该经费数据失败")
    public void deleteFundings(@NotBlank(message = "{required}") @PathVariable int[] fundingIds) {
         fundingService.deleteFundings(fundingIds);
    }

    /*
     *  请求类型：Put
     *  @param funding 经费对象
     *  作用：根据经费id修改经费数据
     */
    @PutMapping("/UpdateFunding")
    @ControllerEndpoint(operation = "修改该经费数据", exceptionMessage = "修改该经费数据失败")
    public void updateFunding(@Valid Funding funding) {
        fundingService.updateFunding(funding);
    }

    /*
     *  请求类型：Get
     *  @param fundingId 经费id
     *  作用：点击修改时，根据fundingID返回经费数据对象，可以选择的申请人和审核人对象供前端修改
     */
    @GetMapping("/selectFundingById")
    public Funding selectFundingById(@NotBlank(message = "{required}") @PathVariable int fundingId) {
        return fundingService.selectFundingById(fundingId);
    }


}