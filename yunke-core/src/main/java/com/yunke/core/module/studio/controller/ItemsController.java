package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Items;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 项目_任务表(Items)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/items")
public class ItemsController {

    private final IItemsService iItemsService;

    /**
     * 新增项目任务_POST
     *
     * @param items  项目_任务 新增参数
     * @param userId  任务成员id, ","分隔
     * @param m_state 任务成员角色, ","分隔   1为负责人，2为成员，3为指导老师
     */
    @PostMapping
    @PreAuthorize("hasAuthority('task:add')")
    @ControllerEndpoint(operation = "新增任务", exceptionMessage = "新增任务失败")
    public void addTask(@Valid Items items, String userId, String m_state) {
        String[] split_userId = StrUtil.split(userId, StrUtil.COMMA); //成员id
        String[] split_state = StrUtil.split(m_state, StrUtil.COMMA);//成员角色
        this.iItemsService.createTask(items, split_userId, split_state);
    }

    /**
     * 删除项目任务_DELETE
     *
     * @param itemsIds 要删除任务的id
     */
    @DeleteMapping("{itemsIds}")
    @PreAuthorize("hasAuthority('task:delete')")
    @ControllerEndpoint(operation = "删除任务", exceptionMessage = "删除任务失败")
    public void deleteTask(@NotBlank(message = "{required}") @PathVariable String itemsIds) {
        String[] ids = itemsIds.split(StrUtil.COMMA);
        this.iItemsService.deleteTask(ids);
    }

    /**
     * 更新项目任务_PUT
     *
     * @param items 要更新的对象
     */
    @PutMapping
    @PreAuthorize("hasAuthority('task:update')")
    @ControllerEndpoint(operation = "更新任务", exceptionMessage = "更新任务失败")
    public void updateTask(@Valid Items items) {
        this.iItemsService.updateTask(items);
    }


    /**
     * 查询项目任务列表_GET
     *
     * @param param  分页数据 (pageNum,pageSize,title,ascending/descending)
     * @param items 模糊查询的对象 (title) ,条件待定
     * @return Page ， Items（title，itemsId）
     */
    @GetMapping
    public R<Map<String, Object>> taskList(QueryParam param, Items items) {
        IPage<Items> result = this.iItemsService.pageTaskList(param, items);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 查询项目任务_GET
     *
     * @param itemsId 项目id
     * @return items 项目详细信息
     */
    @GetMapping("{itemsId}")
    public R<Map<String,Object>> getTask(@NotBlank(message = "{required}") @PathVariable String itemsId) {
        Map<String,Object> items = this.iItemsService.getTask(itemsId);
        return R.ok(items);
    }

    /**
     * 项目任务总条数_GET
     *
     * @return 项目任务总条数
     */
    @GetMapping("count")
    public R<Integer> getTaskCount() {
        return R.ok(this.iItemsService.getAllTaskCount());
    }


}