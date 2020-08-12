package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.common.core.entity.studio.Thesis;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 软件著作权_任务表(Copyright)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface CopyrightMapper extends BaseMapper<Copyright> {


    <T> IPage<Copyright> pageTask(Page<T> page, @Param("copyright") Copyright copyright);

    Map<String,Object> getTask(@Param("copyrightId") String copyrightId);

    void updateState(@Param("copyright")Copyright copyright);
}