package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Thesis;
import com.yunke.core.module.studio.mapper.ThesisMapper;
import com.yunke.core.module.studio.service.IThesisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 论文_任务表(Thesis)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ThesisServiceImpl extends ServiceImpl<ThesisMapper, Thesis> implements IThesisService {

}