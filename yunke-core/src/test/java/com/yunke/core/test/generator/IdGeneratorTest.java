package com.yunke.core.test.generator;

import com.yunke.core.module.studio.generator.IdGenerateUtil;
import com.yunke.core.module.studio.generator.TaskTypeConstant;
import org.junit.Test;

/**
 * @author chachae
 * @version v1.0
 * @date 2020/7/4 15:37
 */
public class IdGeneratorTest {

  @Test
  public void idUtilTestCase1() {
    for (int i = 0; i < 10; i++) {
      String id = IdGenerateUtil.nextId(TaskTypeConstant.COPYRIGHT);
      System.out.println(String.format("id：%s，type：%s", id, IdGenerateUtil.getIdType(id)));
    }
    for (int i = 0; i < 10; i++) {
      String id = IdGenerateUtil.nextId(TaskTypeConstant.ITEMS);
      System.out.println(String.format("id：%s，type：%s", id, IdGenerateUtil.getIdType(id)));
    }
    for (int i = 0; i < 10; i++) {
      String id = IdGenerateUtil.nextId(TaskTypeConstant.MATCH);
      System.out.println(String.format("id：%s，type：%s", id, IdGenerateUtil.getIdType(id)));
    }
    for (int i = 0; i < 10; i++) {
      String id = IdGenerateUtil.nextId(TaskTypeConstant.THESIS);
      System.out.println(String.format("id：%s，type：%s", id, IdGenerateUtil.getIdType(id)));
    }
  }

}

