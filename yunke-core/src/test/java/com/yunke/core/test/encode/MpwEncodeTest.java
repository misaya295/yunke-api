package com.yunke.core.test.encode;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.Test;

/**
 * mpw:key 加密
 *
 * @author chachae
 * @version v1.0
 * @date 2020/6/12 21:40
 */
public class MpwEncodeTest {

  @Test
  public void encodeTestCase1() throws Exception {
    final String data = "";
    // 生成 16 位随机 AES 密钥
    String randomKey = AES.generateRandomKey();
    System.out.println(String.format("%s%s", "key -> ", randomKey));
    // 随机密钥加密
    String result = AES.encrypt(data, randomKey);
    System.out.println(String.format("%s%s", "after encode -> ", result));
  }
}