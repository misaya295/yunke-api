package com.yunke.core.module.studio.generator;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.yunke.common.core.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

/**
 * 四类任务的ID生成器
 *
 * @author chachae
 * @version v1.0
 * @date 2020/7/4 14:23
 */
@Slf4j
public class IdGenerateUtil extends cn.hutool.core.util.IdUtil {

  private IdGenerateUtil() {
  }

  private static final Snowflake SNOW_FLAKE = getSnowflake(1, 1);

  /**
   * 生成一个带任务前缀码的22位ID
   * <pre>
   *   例如，String id = IdUtil.nextId(TaskTypeConstant.COPYRIGHT)
   * </pre>
   *
   * @param typeName 任务类型（从 TaskTypeConstant 常量内选择）
   * @return ID
   */
  public static String nextId(String typeName) {
    if (StrUtil.isBlank(typeName)) {
      return null;
    }
    return TaskTypeEnum.getPrefix(typeName) + SNOW_FLAKE.nextId();
  }

  /**
   * 判断 ID 的类型
   *
   * @param id id
   * @return 类型（对应 TaskTypeConstant）
   */
  public static String getIdType(String id) {
    if (StrUtil.isBlank(id) || id.length() <= 4) {
      throw new ApiException("输入的 ID 有误");
    }
    return TaskTypeEnum.getType(id.substring(0, 3));
  }

}
