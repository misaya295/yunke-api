package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.ICopyrightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 软件著作权_任务表(Copyright)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/copyright")
public class CopyrightController {

    private final ICopyrightService copyrightService;

    /**
     * 新增软件著作权任务_POST
     *
     * @param copyright  软件著作权_任务 新增参数
     * @param userId  任务成员id, ","分隔
     * @param m_state 任务成员角色, ","分隔   1为负责人，2为成员，3为指导老师
     */
    @PostMapping
    @PreAuthorize("hasAuthority('task:add')")
    @ControllerEndpoint(operation = "新增任务", exceptionMessage = "新增任务失败")
    public void addTask(@Valid Copyright copyright, String userId, String m_state) {
        String[] split_userId = StrUtil.split(userId, StrUtil.COMMA); //成员id
        String[] split_state = StrUtil.split(m_state, StrUtil.COMMA);//成员角色
        this.copyrightService.createTask(copyright, split_userId, split_state);
    }

    /**
     * 删除软件著作权任务_DELETE
     *
     * @param copyrightIds 要删除任务的id
     */
    @DeleteMapping("{copyrightIds}")
    @PreAuthorize("hasAuthority('task:delete')")
    @ControllerEndpoint(operation = "删除任务", exceptionMessage = "删除任务失败")
    public void deleteTask(@NotBlank(message = "{required}") @PathVariable String copyrightIds) {
        String[] ids = copyrightIds.split(StrUtil.COMMA);
        this.copyrightService.deleteTask(ids);
    }

    /**
     * 更新软件著作权任务_PUT
     *
     * @param copyright 要更新的对象
     */
    @PutMapping
    @PreAuthorize("hasAuthority('task:update')")
    @ControllerEndpoint(operation = "更新任务", exceptionMessage = "更新任务失败")
    public void updateTask(@Valid Copyright copyright) {
        this.copyrightService.updateTask(copyright);
    }


    /**
     * 查询软件著作权任务列表_GET
     *
     * @param param  分页数据 (pageNum,pageSize,title,ascending/descending)
     * @param copyright 模糊查询的对象 (title) ,条件待定
     * @return Page ， copyright（title，copyrightId）
     */
    @GetMapping
    public R<Map<String, Object>> taskList(QueryParam param, @Valid Copyright copyright) {
        IPage<Copyright> result = this.copyrightService.pageTaskList(param, copyright);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 查询软件著作权任务_GET
     *
     * @param copyrightId 软件著作权id
     * @return copyright 软件著作权详细信息
     */
    @GetMapping("{copyrightId}")
    public R<Map<String,Object>> getTask(@NotBlank(message = "{required}") @PathVariable String copyrightId) {
        Map copyright = this.copyrightService.getTask(copyrightId);
        return R.ok(copyright);
    }

    /**
     * 软件著作权任务总条数_GET
     *
     * @return 软件著作权任务总条数
     */
    @GetMapping("count")
    public R<Integer> getTaskCount() {
        return R.ok(this.copyrightService.getAllTaskCount());
    }


}