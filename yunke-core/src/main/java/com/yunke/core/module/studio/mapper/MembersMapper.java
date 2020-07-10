package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Members;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 任务成员的中间表(Members)表数据库访问层1
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface MembersMapper extends BaseMapper<Members> {


    IPage<Map<String, Object>> pageMembers(Page<Map<String, Object>> page, @Param("taskId") String taskId);
}