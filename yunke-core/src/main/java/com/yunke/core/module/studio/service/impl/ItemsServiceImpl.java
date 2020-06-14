package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.studio.Items;
import com.yunke.core.module.studio.mapper.ItemsMapper;
import com.yunke.core.module.studio.service.IItemsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目_任务表(Items)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

}