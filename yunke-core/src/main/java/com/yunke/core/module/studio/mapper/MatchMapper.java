package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Match;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 比赛_任务表(Match)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:52
 */
public interface MatchMapper extends BaseMapper<Match> {

    <T> IPage<Match> pageTask(Page<T> page, @Param("match") Match match);

    Map<String,Object> getTask(@Param("matchId") String matchId);
}