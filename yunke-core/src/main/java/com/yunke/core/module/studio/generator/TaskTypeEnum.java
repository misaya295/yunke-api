package com.yunke.core.module.studio.generator;

import com.yunke.common.core.exception.ApiException;
import lombok.AllArgsConstructor;

/**
 * 任务类型枚举
 *
 * @author chachae
 * @version v1.0
 * @date 2020/7/4 14:26
 */
@AllArgsConstructor
public enum TaskTypeEnum {

  COPYRIGHT(TaskTypeConstant.COPYRIGHT, "101"),

  ITEM(TaskTypeConstant.ITEMS, "102"),

  MATCH(TaskTypeConstant.MATCH, "103"),

  THESIS(TaskTypeConstant.THESIS, "104");

  /**
   * 任务类型
   */
  private final String type;

  /**
   * ID前缀
   */
  private final String prefix;


  /**
   * 获取ID前缀码
   *
   * @param typeName 类型名称（从 TaskTypeConstant 常量内选择）
   * @return 返回前缀码
   */
  public static String getPrefix(String typeName) {

    if (typeName == null) {
      throw new ApiException("类型任务不能为空");
    }

    for (TaskTypeEnum curEnum : TaskTypeEnum.values()) {
      if (curEnum.type.equals(typeName)) {
        return curEnum.prefix;
      }
    }
    throw new ApiException("不存在此类型的任务");
  }

  /**
   * 获取ID前缀码
   *
   * @param prefix ID前缀码
   * @return 返回前缀码
   */
  public static String getType(String prefix) {

    for (TaskTypeEnum curEnum : TaskTypeEnum.values()) {
      if (curEnum.prefix.equals(prefix)) {
        return curEnum.type;
      }
    }
    return null;
  }
}


