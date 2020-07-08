package com.yunke.core.module.oss.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.yunke.common.core.entity.oss.QiNiuConfig;
import com.yunke.common.core.entity.oss.QiNiuContent;
import com.yunke.common.core.entity.oss.query.QiNiuQueryDto;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.FileUtil;
import com.yunke.core.module.oss.mapper.QiNiuContentMapper;
import com.yunke.core.module.oss.service.IQiNiuConfigService;
import com.yunke.core.module.oss.service.IQiNiuContentService;
import com.yunke.core.module.oss.util.QiNiuUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 七牛云文件存储表服务实现类
 *
 * @author chachae
 * @since 2020-06-27 13:21:53
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QiNiuContentServiceImpl extends
    ServiceImpl<QiNiuContentMapper, QiNiuContent> implements
    IQiNiuContentService {

  private final IQiNiuConfigService qiNiuConfigService;

  /**
   * 文件最大上传数
   */
  @Value("${qiNiu.max-size}")
  private Long maxSize;

  /**
   * token超时时间
   */
  @Value("${qiNiu.expire-in-second}")
  private Long expire;

  @Override
  public IPage<QiNiuContent> pageQiNiuContent(QiNiuQueryDto query) {
    LambdaQueryWrapper<QiNiuContent> wrapper = new LambdaQueryWrapper<>();
    if (StrUtil.isNotEmpty(query.getKey())) {
      wrapper.like(QiNiuContent::getName, query.getKey());
    }
    return baseMapper.selectPage(new Page<>(query.getPageNum(), query.getPageSize()), wrapper);
  }

  @Override
  public List<QiNiuContent> getByIds(String[] ids) {
    return baseMapper.selectBatchIds(Arrays.asList(ids));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public QiNiuContent upload(MultipartFile file, QiNiuConfig qiNiuConfig) {
    FileUtil.checkSize(maxSize, file.getSize());
    if (qiNiuConfigService.getConfig() == null) {
      throw new ApiException("请先添加七牛云对象存储配置后再操作");
    }
    Configuration cfg = new Configuration(QiNiuUtil.getRegion(qiNiuConfig.getZone()));
    UploadManager uploadManager = new UploadManager(cfg);
    Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getSecretKey());
    String upToken = auth.uploadToken(qiNiuConfig.getBucket());
    try {
      String key = file.getOriginalFilename();
      if (getByName(key) != null) {
        key = QiNiuUtil.getKey(key);
      }
      Response response = uploadManager.put(file.getBytes(), key, upToken);
      DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
      QiNiuContent content = getByName(FileUtil.getFileNameNoEx(putRet.key));
      if (content == null) {
        QiNiuContent qiniuContent = new QiNiuContent();
        qiniuContent.setSuffix(FileUtil.getExtensionName(putRet.key));
        qiniuContent.setBucket(qiNiuConfig.getBucket());
        qiniuContent.setType(qiNiuConfig.getType());
        qiniuContent.setName(FileUtil.getFileNameNoEx(putRet.key));
        qiniuContent.setUrl(qiNiuConfig.getHost() + "/" + putRet.key);
        qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(file.getSize() + "")));
        qiniuContent.setUpdateTime(new Date());
        baseMapper.insert(qiniuContent);
        return qiniuContent;
      }
      return content;
    } catch (Exception e) {
      throw new ApiException(e.getMessage());
    }
  }

  @Override
  public QiNiuContent getByName(String key) {
    LambdaQueryWrapper<QiNiuContent> qw = new LambdaQueryWrapper<>();
    qw.eq(QiNiuContent::getName, key);
    return baseMapper.selectOne(qw);
  }

  @Override
  public QiNiuContent getById(Long id) {
    return baseMapper.selectById(id);
  }

  @Override
  public String download(QiNiuContent content, QiNiuConfig config) {
    String finalUrl;
    if (QiNiuConfig.OPEN_ZONE.equals(content.getType())) {
      finalUrl = content.getUrl();
    } else {
      // 私有空间
      Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
      finalUrl = auth.privateDownloadUrl(content.getUrl(), expire);
    }
    return finalUrl;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void delete(String[] contentIds, QiNiuConfig config) {
    baseMapper.deleteBatchIds(Arrays.asList(contentIds));
    List<QiNiuContent> contents = getByIds(contentIds);
    deleteOssFiles(contents, config);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void synchronize(QiNiuConfig config) {
    if (config.getConfigId() == null) {
      throw new ApiException("请先添加相应配置，再操作");
    }
    Configuration cfg = new Configuration(QiNiuUtil.getRegion(config.getZone()));
    Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
    BucketManager bucketManager = new BucketManager(auth, cfg);
    String prefix = "";
    int limit = 1000;
    String delimiter = "";
    BucketManager.FileListIterator fileListIterator = bucketManager
        .createFileListIterator(config.getBucket(), prefix, limit, delimiter);
    while (fileListIterator.hasNext()) {
      QiNiuContent qiNiuContent;
      FileInfo[] items = fileListIterator.next();
      for (FileInfo item : items) {
        if (getByName(FileUtil.getFileNameNoEx(item.key)) == null) {
          qiNiuContent = new QiNiuContent();
          qiNiuContent.setSize(FileUtil.getSize(Integer.parseInt("" + item.fsize)));
          qiNiuContent.setSuffix(FileUtil.getExtensionName(item.key));
          qiNiuContent.setName(FileUtil.getFileNameNoEx(item.key));
          qiNiuContent.setType(config.getType());
          qiNiuContent.setBucket(config.getBucket());
          qiNiuContent.setUrl(config.getHost() + "/" + item.key);
          qiNiuContent.setUpdateTime(new Date());
          baseMapper.insert(qiNiuContent);
        }
      }
    }
  }

  @Override
  public void deleteAll(String[] ids, QiNiuConfig config) {
    delete(ids, config);
  }

  @Override
  public void deleteOssFiles(List<QiNiuContent> contents, QiNiuConfig config) {
    Configuration cfg = new Configuration(QiNiuUtil.getRegion(config.getZone()));
    Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
    BucketManager bucketManager = new BucketManager(auth, cfg);
    for (QiNiuContent content : contents) {
      try {
        bucketManager.delete(content.getBucket(), content.getName() + "." + content.getSuffix());
      } catch (QiniuException e) {
        log.error("QiNiuException", e);
        throw new ApiException("七牛云对象存储服务异常");
      }
    }
  }

  @Override
  public List<Map<String, Object>> getTopTenFileTypeData() {
    return baseMapper.listCountTopTenFileTypeData();
  }
}