package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Match;

import java.util.Map;

/**
 * 比赛_任务表(Match)表服务接口1
 *
 * @author chachae
 * @since 2020-06-14 14:04:53
 */
public interface IMatchService extends IService<Match> {

    /**
     * 新增比赛任务
     *
     * @param match 比赛任务对象
     * @param userId 成员id
     * @param state  成员角色
     */
    void createTask(Match match, String[] userId, String[] state);

    /**
     * 删除比赛任务
     *
     * @param ids 要删除对象的ids
     */
    void deleteTask(String[] ids);

    /**
     * 更新比赛任务
     *
     * @param match 要更新的比赛对象
     */
    void updateTask(Match match);

    /**
     * 查询比赛任务列表
     *
     * @param param 分页数据
     * @param match 条件查询的比赛对象
     */
    IPage<Match> pageTaskList(QueryParam param, Match match);

    /**
     * id查询比赛任务
     *
     * @param matchId 比赛id
     */
    Match getTask(String matchId);

    /**
     * 查询所有项目条数
     * @return 返回所有项目条数
     */
    Integer getAllTaskCount();

    /**
     * 修改任务状态
     *
     * @param match
     */
    void updateState(Match match);
}