package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.MatchMemberAwards;
import com.yunke.core.module.studio.mapper.MatchMemberAwardsMapper;
import com.yunke.core.module.studio.service.IMatchMemberAwardsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 比赛人员奖项表(MatchMemberAwards)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MatchMemberAwardsServiceImpl extends
    ServiceImpl<MatchMemberAwardsMapper, MatchMemberAwards> implements
    IMatchMemberAwardsService {

}