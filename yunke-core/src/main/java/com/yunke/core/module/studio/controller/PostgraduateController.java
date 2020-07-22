package com.yunke.core.module.studio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Postgraduate;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IPostgraduateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 考研人员表(Postgraduate)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/postgraduate")
public class PostgraduateController {

    private final IPostgraduateService postgraduateService;

    /**
     * 添加考研_POST
     *
     * @param postgraduate  添加考研的参数
     */
    @PostMapping
    @ControllerEndpoint(operation = "添加考研", exceptionMessage = "添加考研失败")
    public void addPostgraduate(@Valid Postgraduate postgraduate){
            this.postgraduateService.addPostgraduate(postgraduate);
    }

    /**
     * 修改考研信息_PUT
     *
     * @param postgraduate  修改考研信息的参数
     */
    @PutMapping
    @ControllerEndpoint(operation = "修改考研信息", exceptionMessage = "修改考研信息失败")
    public void updatePostgraduate(@Valid Postgraduate postgraduate){
            this.postgraduateService.updatePostgraduate(postgraduate);
    }


    /**
     * 查询所有考研信息_GET
     *
     * @param param
     * @param postgraduate
     * @return  考研信息列表
     */
    @GetMapping
    public R<Map<String, Object>> postgraduateList(QueryParam param,Postgraduate postgraduate){
        IPage<Postgraduate> result = this.postgraduateService.postgraduateList(param, postgraduate);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     *  通过id查询考研信息_GET
     *
     * @param id 考研id
     * @return  考研信息
     */
    @GetMapping("{id}")
    public R<Map<String, Object>> getPostgraduate(@NotBlank(message = "{required}") @PathVariable String id){
       Map postgraduate =  this.postgraduateService.getPostgraduate(id);
        return R.ok(postgraduate);
    }


}