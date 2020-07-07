package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Thesis;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 论文_任务表(Thesis)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface ThesisMapper extends BaseMapper<Thesis> {

   <T> IPage<Thesis> pageTask(Page<T> page,@Param("thesis") Thesis thesis);

   Map<String,Object> getTask(@Param("thesisId") String thesisId);
}