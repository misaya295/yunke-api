package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.yunke.common.core.entity.studio.Thesis;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IThesisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

/**
 * 论文_任务表(Thesis)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:57
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/thesis")
public class ThesisController {

    private final IThesisService thesisService;

    /**
     * 新增论文任务_POST
     * @param thesis  论文_任务 新增参数
     * @return 空
     */
    @PostMapping
    @PreAuthorize("hasAuthority('task:add')")
    @ControllerEndpoint(operation = "新增任务", exceptionMessage = "新增任务失败")
    public void addTask(@Valid @RequestBody Thesis thesis) {
        //TODO @RequestBody测试后删除
        this.thesisService.createTask(thesis);
    }

    /**
     *
     * @param thesisUuidIds  要删除任务的id
     */
    @DeleteMapping("{thesisUuidIds}")
    @PreAuthorize("hasAuthority('task:delete')")
    @ControllerEndpoint(operation = "删除任务", exceptionMessage = "删除任务失败")
    public void deleteTask(@NotBlank(message = "{required}") @PathVariable String thesisUuidIds) {
        String[] ids = thesisUuidIds.split(StrUtil.COMMA);
        System.out.println(Arrays.toString(ids));
//        this.thesisService.deleteTask(ids);
    }


}