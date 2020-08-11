package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunke.common.core.entity.studio.MatchMemberAwards;
import org.apache.ibatis.annotations.Param;

/**
 * 比赛人员奖项表(MatchMemberAwards)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface MatchMemberAwardsMapper extends BaseMapper<MatchMemberAwards> {

    void saveMatchMemberAwards(@Param("memberAwards") MatchMemberAwards memberAwards);

    void updateMemberAwards(@Param("memberAwards")MatchMemberAwards memberAwards);
}