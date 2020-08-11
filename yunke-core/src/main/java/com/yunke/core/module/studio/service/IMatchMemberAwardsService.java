package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.studio.MatchMemberAwards;

/**
 * 比赛人员奖项表(MatchMemberAwards)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface IMatchMemberAwardsService extends IService<MatchMemberAwards> {

    /**
     * 添加用户比赛获奖
     * @param memberAwards
     */
    void saveMatchMemberAwards(MatchMemberAwards memberAwards);

    /**
     * 修改用户比赛获奖
     * @param memberAwards
     */
    void updateMemberAwards(MatchMemberAwards memberAwards);
}