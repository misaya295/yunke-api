package com.yunke.core.util;

import cn.hutool.core.util.StrUtil;
import com.yunke.common.core.constant.SystemConstant;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;

/**
 * IP 地理位置获取
 *
 * @author chachae
 * @date 2020/6/6 22:32
 */
@Slf4j
public class AddressUtil {

  private AddressUtil() {
  }

  public static String getCityInfo(String ip) {
    DbSearcher searcher = null;
    try {
      String dbPath = AddressUtil.class.getResource("/ip2region/ip2region.db").getPath();
      File file = new File(dbPath);
      if (!file.exists()) {
        String tmpDir = System.getProperties().getProperty(SystemConstant.JAVA_TEMP_DIR);
        dbPath = tmpDir + "ip.db";
        file = new File(dbPath);
        InputStream resourceAsStream = AddressUtil.class.getClassLoader()
            .getResourceAsStream("classpath:ip2region/ip2region.db");
        if (resourceAsStream != null) {
          FileUtils.copyInputStreamToFile(resourceAsStream, file);
        }
      }
      DbConfig config = new DbConfig();
      searcher = new DbSearcher(config, file.getPath());
      Method method = searcher.getClass().getMethod("btreeSearch", String.class);
      DataBlock dataBlock = (DataBlock) method.invoke(searcher, ip);
      return dataBlock.getRegion();
    } catch (Exception e) {
      log.warn("获取地址信息异常,{}", e.getMessage());
      return StrUtil.EMPTY;
    } finally {
      if (searcher != null) {
        try {
          searcher.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}