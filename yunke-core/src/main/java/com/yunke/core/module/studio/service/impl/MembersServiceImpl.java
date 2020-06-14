package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.core.module.studio.mapper.MembersMapper;
import com.yunke.core.module.studio.service.IMembersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务成员的中间表(Members)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements
    IMembersService {

}