package com.yunke.core.module.studio.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.GraduatedCorporation;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IFundingService;
import com.yunke.core.module.studio.service.IGraduatedCorporation;
import com.yunke.core.module.studio.service.impl.GraduatedCorporationImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 毕业去向表(GraduatedCorporation)表控制层
 *
 * @author Pning
 * @since 2020-07-13 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/graduated/corporation")
public class GraduatedCorporationController {
    private final IGraduatedCorporation iGraduatedCorporation;

    /**
     * 请求类型：GET
     * @param param 页数
     * @param graduatedCorporation 模糊查询的条件
     * 作用：根据页数param.pageNum查询满足条件的前10条的经费数据
     */
    @GetMapping
    public R<Map<String, Object>> GraduatedCorporationListBypage(QueryParam param, GraduatedCorporation graduatedCorporation) {
        IPage<GraduatedCorporation> result = this.iGraduatedCorporation.pageGraduatedCorporation(param,graduatedCorporation);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 请求类型：GET
     * @param userId 用户id
     * 作用：通过用户userId返回该用户的毕业去向数据
     */
    @GetMapping("/{userId}")
    @ControllerEndpoint(operation = "查询该成员的毕业去向成功", exceptionMessage = "查询该成员的毕业去向失败")
    public R<GraduatedCorporation> selectGraduatedCorporationByUserId(@PathVariable("userId") int userId) {
        return R.ok(this.iGraduatedCorporation.selectGraduatedCorporationById(userId));
    }

    /*
     *  请求类型：Put
     *  @param funding 经费对象
     *  作用：根据经费id修改经费数据
     */
    @PutMapping
    @ControllerEndpoint(operation = "修改成员的毕业去向", exceptionMessage = "修改成员的毕业去向失败")
    public void updateFunding(@Valid GraduatedCorporation graduatedCorporation) {
        if(graduatedCorporation.getUserId()!=null && graduatedCorporation.getCorporationName()!=null && graduatedCorporation.getCorporationName()!="") {
            this.iGraduatedCorporation.updateGraduatedCorporationMessage(graduatedCorporation);
        }else{
            throw new ApiException("用户id与公司名称不能为空，修改毕业去向失败");
        }
    }

    /*
     *  请求类型：post
     *  @param funding 经费对象
     *  作用：添加经费申请，经费对象的name,proposer_id和apply_time不能为空，proposer_id为当前登录的用户user_id
     */
    @PostMapping
    @ControllerEndpoint(operation = "添加毕业去向", exceptionMessage = "添加毕业去向失败")
    public void addGraduatedCorporation(@Valid GraduatedCorporation graduatedCorporation) {
        if(graduatedCorporation.getUserId()!=null&&graduatedCorporation.getCorporationName()!=null&&graduatedCorporation.getCorporationName()!="") {
            this.iGraduatedCorporation.addGraduatedCorporation(graduatedCorporation);
        }else{
            throw new ApiException("用户id与公司名称不能为空，添加毕业去向失败");
        }
    }

    /*
     *  请求类型：Delete
     *  @param fundingIds 经费id,每个id之间逗号连接
     *  作用：根据经费id删除数据
     */
    @DeleteMapping("{userIds}")
    @ControllerEndpoint(operation = "删除该学生的毕业去向", exceptionMessage = "删除该经费数据失败")
    public void deleteGraduatedCorporations(@PathVariable("userIds") String userIds) {
        int[] split_userIds = StrUtil.splitToInt(userIds, StrUtil.COMMA);
        if(split_userIds.length>0){
            this.iGraduatedCorporation.deleteGraduatedCorporationByUserIds(split_userIds);
        }
    }
}