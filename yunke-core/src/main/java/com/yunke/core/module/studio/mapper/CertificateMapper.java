package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.Certificate;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 资格证表(Certificate)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface CertificateMapper extends BaseMapper<Certificate> {
    IPage<Certificate> pageCertificate(Page<Certificate> page, @Param("certificate") Certificate certificate);

    Map<String,Object> getCertificate(@Param("id") String id);
}