package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Match;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IMatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;


/**
 * 比赛_任务表(Match)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:55
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/match")
public class MatchController {

    private final IMatchService matchService;

    /**
     * 新增比赛任务_POST
     *
     * @param match   比赛_任务 新增参数
     */
    @PostMapping
    @PreAuthorize("hasAuthority('task:add')")
    @ControllerEndpoint(operation = "新增任务", exceptionMessage = "新增任务失败")
    public void addTask(@Valid Match match) {
        String[] split_userId = StrUtil.split(match.getUserId(), StrUtil.COMMA); //成员id
        String[] split_state = StrUtil.split(match.getM_state(), StrUtil.COMMA);//成员角色
        this.matchService.createTask(match, split_userId, split_state);
    }

    /**
     * 删除比赛任务_DELETE
     *
     * @param matchIds 要删除任务的id
     */
    @DeleteMapping("{matchIds}")
    @PreAuthorize("hasAuthority('task:delete')")
    @ControllerEndpoint(operation = "删除任务", exceptionMessage = "删除任务失败")
    public void deleteTask(@NotBlank(message = "{required}") @PathVariable String matchIds) {
        String[] ids = matchIds.split(StrUtil.COMMA);
        this.matchService.deleteTask(ids);
    }

    /**
     * 更新比赛任务_PUT
     *
     * @param match 要更新的对象
     */
    @PutMapping
    @PreAuthorize("hasAuthority('task:update')")
    @ControllerEndpoint(operation = "更新任务", exceptionMessage = "更新任务失败")
    public void updateTask(@Valid Match match) {
        this.matchService.updateTask(match);
    }


    /**
     * 查询比赛任务列表_GET
     *
     * @param param 分页数据 (pageNum,pageSize,title,ascending/descending)
     * @param match 模糊查询的对象 (title) ,条件待定
     * @return Page ， Match（title，matchId）
     */
    @GetMapping
    public R<Map<String, Object>> taskList(QueryParam param, Match match) {
        IPage<Match> result = this.matchService.pageTaskList(param, match);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 查询比赛任务_GET
     *
     * @param matchId 比赛id
     * @return match 比赛详细信息
     */
    @GetMapping("{matchId}")
    public R<Map<String, Object>> getTask(@NotBlank(message = "{required}") @PathVariable String matchId) {
        Map<String, Object> match = this.matchService.getTask(matchId);
        return R.ok(match);
    }

    /**
     * 比赛任务总条数_GET
     *
     * @return 比赛任务总条数
     */
    @GetMapping("count")
    public R<Integer> getTaskCount() {
        return R.ok(this.matchService.getAllTaskCount());
    }

}