package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Match;
import com.yunke.core.module.studio.mapper.MatchMapper;
import com.yunke.core.module.studio.service.IMatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 比赛_任务表(Match)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:54
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MatchServiceImpl extends ServiceImpl<MatchMapper, Match> implements IMatchService {

}