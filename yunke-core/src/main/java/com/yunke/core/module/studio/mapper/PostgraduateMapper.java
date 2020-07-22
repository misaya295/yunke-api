package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Postgraduate;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 考研人员表(Postgraduate)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface PostgraduateMapper extends BaseMapper<Postgraduate> {

    IPage<Postgraduate> pagePostgraduate(Page<Postgraduate> page, @Param("postgraduate") Postgraduate postgraduate);

    Map getPostgraduate(@Param("id") String id);
}