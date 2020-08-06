package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Certificate;

import java.util.Map;

/**
 * 资格证表(Certificate)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface ICertificateService extends IService<Certificate> {

    /**
     * 添加考证
     *
     * @param certificate  添加考证的参数
     */
    void addCertificate(Certificate certificate);

    /**
     * 修改考证信息_PUT
     *
     * @param certificate  修改考证的参数
     */
    void updateCertificate(Certificate certificate);

    /**
     * 查询所有考证信息
     *
     * @param param
     * @param certificate
     * @return 考证分页信息列表
     */
    IPage<Certificate> certificateList(QueryParam param, Certificate certificate);

    /**
     *  通过id查询考证信息_GET
     *
     * @param id 考证id
     * @return  考证信息
     */
    Map<String,Object> getCertificate(String id);

    /**
     * 删除考证_DELETE
     *
     * @param ids 要删除的id
     */
    void deleteCertificate(String[] ids);
}