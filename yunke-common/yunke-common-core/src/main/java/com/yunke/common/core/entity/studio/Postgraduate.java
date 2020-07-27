package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 考研人员表(Postgraduate)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_postgraduate")
public class Postgraduate implements Serializable {

  /**
   * 考研ID
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 考研人ID
   */
  private Integer userId;
  /**
   * 报考时间
   */
  private String time;
  /**
   * 报考学校
   */
  private String school;
  /**
   * 报考方向
   */
  private String orientation;
  /**
   * 统考成绩
   */
  private String exam;
  /**
   * 状态（0:正在考取/1:已完成）
   */
  private Integer state;
  /**
   * 通过状态(0:失败/1:成功)
   */
  private Integer success;
  /**
   * 姓名
   */
  @TableField(exist = false)
  private String fullName;

}