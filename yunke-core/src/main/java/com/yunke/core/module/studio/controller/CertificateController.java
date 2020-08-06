package com.yunke.core.module.studio.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Certificate;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.ICertificateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 资格证表(Certificate)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/certificate")
public class CertificateController {

    private final ICertificateService certificateService;

    /**
     * 添加考证_POST
     *
     * @param certificate 添加考证的参数
     */
    @PostMapping
    @ControllerEndpoint(operation = "添加考证", exceptionMessage = "添加考证失败")
    public void addCertificate(@Valid Certificate certificate) {
        this.certificateService.addCertificate(certificate);
    }

    /**
     * 修改考证信息_PUT
     *
     * @param certificate 修改考证信息的参数
     */
    @PutMapping
    @ControllerEndpoint(operation = "修改考证信息", exceptionMessage = "修改考证信息失败")
    public void updateCertificate(@Valid Certificate certificate) {
        this.certificateService.updateCertificate(certificate);
    }


    /**
     * 查询所有考证信息_GET
     *
     * @param param
     * @param certificate
     * @return 考证信息列表
     */
    @GetMapping
    public R<Map<String, Object>> postgraduateList(QueryParam param, Certificate certificate) {
        System.out.println(certificate.getState());
        IPage<Certificate> result = this.certificateService.certificateList(param, certificate);
        return R.ok(PageUtil.toPage(result));
    }

    /**
     * 通过id查询考证信息_GET
     *
     * @param id 考证id
     * @return 考证信息
     */
    @GetMapping("{id}")
    public R<Map<String, Object>> getPostgraduate(@NotBlank(message = "{required}") @PathVariable String id) {
        Map<String,Object> certificate = this.certificateService.getCertificate(id);
        return R.ok(certificate);
    }

    /**
     * 删除考证_DELETE
     *
     * @param ids 要删除的id
     */
    @DeleteMapping("{ids}")
    public void deleteTask(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] ids_split = ids.split(StrUtil.COMMA);
        this.certificateService.deleteCertificate(ids_split);
    }


}