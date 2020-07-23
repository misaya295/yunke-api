package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IFundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        IPage<Funding> result = fundingService.pageFunding(param,funding);
        //int count = fundingService.pageFundingCount(funding);//符合该条件的个数,page自己封装好了无需自己写，这里先留着，看后面会不会用到类似的,删了怪可惜的
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 查询时间范围内的花费/入账/剩余资金
     * 请求类型：GET
     * @param timeAndStata 开始时间,结束时间,查询类型:-1/0/1,分别对应:开销/剩余/入账
     */
    @GetMapping("/bill/{timeAndStata}")
    @ControllerEndpoint(operation = "查询经费账单", exceptionMessage = "查询经费账单失败")
    public R<Double> queryFundingBillByTime(@PathVariable("timeAndStata")String timeAndStata) {
       String[] split_time = new String[3];
       split_time = StrUtil.split(timeAndStata, StrUtil.COMMA);
       System.out.println(Arrays.toString(split_time));
       return R.ok(fundingService.queryFundingCostByTime(split_time[0],split_time[1],split_time[2]));
    }

    /*
     *  请求类型：Delete
     *  @param fundingIds 经费id,每个id之间逗号连接
     *  作用：根据经费id删除数据
     */
    @DeleteMapping("/{fundingIds}")
    @ControllerEndpoint(operation = "删除该经费数据", exceptionMessage = "删除该经费数据失败")
    public void deleteFundings(@PathVariable("fundingIds") String fundingIds) {
        int[] split_fundingIds = StrUtil.splitToInt(fundingIds, StrUtil.COMMA);
        if(split_fundingIds.length>0){
            fundingService.deleteFundings(split_fundingIds);
        }else if(split_fundingIds.length == 0){
           throw new ApiException("前端传入的经费id为空，删除失败");
        }
    }

    /*
     *  请求类型：Put
     *  @param funding 经费对象
     *  作用：根据经费id修改经费数据
     */
    @PutMapping("/{funding}")
    @ControllerEndpoint(operation = "修改该经费数据", exceptionMessage = "修改该经费数据失败")
    public void updateFunding(@PathVariable("funding") Funding funding) {
        if(funding!=null){
            if(funding.getName()!=""&&funding.getName()!=null && funding.getProposerId()!=0&&funding.getProposerId()!=null&&funding.getApplyTime()!=null&&funding.getApplyTime()!="") {
                fundingService.updateFundingMessage(funding);
            }else{
                throw new ApiException("经费申请里面的必填数据为空，修改失败");
            }
        }else{
            throw new ApiException("不能把所有数据都改为空");
        }
    }

    /*
     *  请求类型：Get
     *  @param fundingId 经费id
     *  作用：点击修改/查询时，根据fundingID返回经费数据对象，可以选择的申请人和审核人的对象（对象数据只有用户id和名字）
     *      如果经费id为-1的话则只返回可以选择的申请人和审核人的对象（对象数据只有用户id和名字）
     */
    @GetMapping("/{fundingId}")
    @ControllerEndpoint(operation = "查询经费申请数据和选择的申请人和审核人成功", exceptionMessage = "查询经费申请数据和选择的申请人和审核人失败")
    public R<List<Object>> selectFundingById(@PathVariable("fundingId") int fundingId) {
        List<Object> message = new ArrayList<>();
        //该经费id的数据
        Funding funding =fundingService.selectFundingById(fundingId);
        if(funding!=null) {
            message.add(funding);
            //可选择的申请人(所有人都可以申请)（只有id和真实名称）
            int[] verifierRoleId = {1, 2, 3, 4};
            message.add(fundingService.selectUserNameByRoleId(verifierRoleId));
            //可选择的审核人(只有管理员可以申请)（只有id和真实名称）
            int[] certifierRoleId = {1};
            message.add(fundingService.selectUserNameByRoleId(certifierRoleId));
            return R.ok(message);
        }else{
            throw new ApiException("查询不到这个经费");
        }
    }
    /*
     *  请求类型：post
     *  @param funding 经费对象
     *  作用：添加经费申请，经费对象的name,proposer_id和apply_time不能为空，proposer_id为当前登录的用户user_id
     */
    @PostMapping("/{funding}")
    @ControllerEndpoint(operation = "添加经费申请", exceptionMessage = "添加经费申请失败")
    public void addFunding(@PathVariable("funding")Funding funding) {
        if(funding!=null){
            fundingService.addFunding(funding);
        }else{
            throw new ApiException("添加的经费申请里面什么数据都没有");
        }
    }

    /*
     *  请求类型：Put
     *  @param funding 经费对象
     *  作用：修改经费申请状态，1申请中/2报销中/3报销成功/4申请失败
     */
    @PutMapping("/state/{funding}")
    @ControllerEndpoint(operation = "修改该经费申请状态", exceptionMessage = "修改该经费申请状态失败")
    public void updateFundingState(@PathVariable("funding") Funding funding) {
        if(funding!=null){
                fundingService.updateFundingState(funding);
        }else{
            throw new ApiException("经费申请状态不能改为空");
        }
    }

}