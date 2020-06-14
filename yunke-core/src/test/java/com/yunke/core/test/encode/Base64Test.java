package com.yunke.core.test.encode;

import org.apache.logging.log4j.util.Base64Util;
import org.junit.Test;

/**
 * @author chachae
 * @version v1.0
 * @date 2020/6/12 12:03
 */
public class Base64Test {

  @Test
  public void encodeTestCase1() {
    final String password = "yunke:123456";
    System.out.println(Base64Util.encode(password));
  }

}
