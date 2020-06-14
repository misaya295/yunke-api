package com.yunke.core.test;

import com.yunke.core.YunkeCoreApplication;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chachae
 * @version v1.0
 * @date 2020/6/12 21:39
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {YunkeCoreApplication.class})
public class YunkeCoreApplicationTest {

  /**
   * Example
   */
  @Test
  public void testCase1() {
    TestCase.assertEquals(1, 1);
  }

}
