package com.yunke.core.module.oss.util;

import cn.hutool.core.util.StrUtil;
import com.qiniu.storage.Region;
import com.yunke.common.core.util.DateUtil;
import com.yunke.common.core.util.FileUtil;

/**
 * 七牛云操作工具类
 *
 * @author chachae
 * @version v1.0
 * @date 2020/6/27 11:50
 */
public class QiNiuUtil {

  private QiNiuUtil() {
  }

  private static final String EAST_CHINA = "华东";
  private static final String NORTH_CHINA = "华北";
  private static final String SOUTH_CHINA = "华南";
  private static final String NORTH_AMERICA = "北美";

  /**
   * 得到机房的对应关系
   *
   * @param zone 机房名称
   * @return Region
   */
  public static Region getRegion(String zone) {

    if (EAST_CHINA.equals(zone)) {
      return Region.huadong();
    } else if (NORTH_CHINA.equals(zone)) {
      return Region.huabei();
    } else if (SOUTH_CHINA.equals(zone)) {
      return Region.huanan();
    } else if (NORTH_AMERICA.equals(zone)) {
      return Region.beimei();
    } else {
      return Region.qvmHuadong();
    }
  }

  /**
   * 默认不指定key的情况下，以文件内容的hash值作为文件名
   *
   * @param file 文件名
   * @return String
   */
  public static String getKey(String file) {
    return FileUtil.getFileNameNoEx(file) + StrUtil.DASHED + DateUtil.formatFullTimeNow()
        + StrUtil.DOT + FileUtil.getExtensionName(file);
  }

}