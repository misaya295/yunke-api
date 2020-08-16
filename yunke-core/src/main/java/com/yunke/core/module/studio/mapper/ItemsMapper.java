package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.common.core.entity.studio.Items;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 项目_任务表(Items)表数据库访问层1
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface ItemsMapper extends BaseMapper<Items> {

    <T> IPage<Items> pageTask(Page<T> page, @Param("items") Items items);

    Map<String,Object> getTask(@Param("itemsId") String itemsId);

    Copyright getCopyright(@Param("itemId") String id);

    void updateState(@Param("items") Items items);
}