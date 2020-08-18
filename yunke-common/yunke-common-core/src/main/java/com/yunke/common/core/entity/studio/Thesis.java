package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 论文_任务表(Thesis)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_thesis")
public class Thesis implements Serializable {

  /**
   * 论文ID
   */
  @TableId(type = IdType.INPUT)
  private String thesisId;
  /**
   * 标题
   */
  private String title;
  /**
   * 摘要
   */
  private String introduction;
  /**
   * 论文类型(1核心/ 2普通）
   */
  private Integer paperType;
  /**
   * 更新时间
   */
  private String time;
  /**
   * 论文下载
   */
  private String url;
  /**
   * 花费
   */
  private Object cost;
  /**
   * 状态（1进行中/2已完成）
   */
  private Integer state;
  /**
   * 发票
   */
  private String invoice;
  /**
   * 是否已报销（0否/1是）
   */
  private Integer reimbursement;
  /**
   * 姓名
   */
  @TableField(exist = false)
  private String fullName;
  /**
   * 负责人姓名
   */
  @TableField(exist = false)
  private String chargeFullName;
  /**
   * 指导老师姓名
   */
  @TableField(exist = false)
  private String teacherFullName;
  /**
   * 成员id ","分隔
   */
  @TableField(exist = false)
  private String userId;
  /**
   * 成员角色 ","分隔   1为负责人，2为成员，3为指导老师
   */
  @TableField(exist = false)
  private String  m_state;
  /**
   * 成员角色 ","分隔   1为负责人，2为成员，3为指导老师
   */
  @TableField(exist = false)
  private String  userState;
  /**
   * 任务成员
   */
  @TableField(exist = false)
  private List<Members> members;
}
