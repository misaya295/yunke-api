package com.yunke.core.module.oss.controller;

import cn.hutool.core.util.StrUtil;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.oss.QiNiuContent;
import com.yunke.common.core.entity.oss.query.QiNiuQueryDto;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.module.oss.service.IQiNiuConfigService;
import com.yunke.core.module.oss.service.IQiNiuContentService;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 七牛云图片处理控制层
 * <p>
 * 远端调用示例：http://domian:port/oss-qiniu/content | http://OES-OSS-Qiniu/oss-qiniu/content
 * </p>
 *
 * @author chachae
 * @version v1.0
 * @date 2020/6/27 13:12
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("oss/content")
public class QiNiuContentController {

  private final IQiNiuConfigService qiNiuConfigService;
  private final IQiNiuContentService qiNiuContentService;

  @GetMapping
  public R<Map<String, Object>> page(QiNiuQueryDto query) {
    return R.ok(PageUtil.toPage(qiNiuContentService.pageQiNiuContent(query)));
  }

  /**
   * 统一上传接口
   *
   * @param file MultipartFile 对象
   * @return 七牛云文件信息
   */
  @PostMapping
  public R<QiNiuContent> upload(@RequestParam("file") MultipartFile file) {
    QiNiuContent content = qiNiuContentService.upload(file, qiNiuConfigService.getConfig());
    return R.ok(content);
  }

  /**
   * 统一文件下载接口
   *
   * @param id 文件编号
   * @return 文件链接
   */
  @GetMapping(value = "/download/{id}")
  public R<String> download(@PathVariable Long id) {
    return R.ok(qiNiuContentService
        .download(qiNiuContentService.getById(id), qiNiuConfigService.getConfig()));
  }

  @PostMapping(value = "/sync")
  public R<Object> synchronize() {
    qiNiuContentService.synchronize(qiNiuConfigService.getConfig());
    return R.ok();
  }

  @DeleteMapping("{ids}")
  public void delete(@PathVariable @NotBlank(message = "{required}") String ids) {
    String[] idArray = ids.split(StrUtil.COMMA);
    qiNiuContentService.delete(idArray, qiNiuConfigService.getConfig());
  }

  @GetMapping("top10")
  public R<List<Map<String, Object>>> countTopTenTypeFiles() {
    return R.ok(qiNiuContentService.getTopTenFileTypeData());
  }
}
