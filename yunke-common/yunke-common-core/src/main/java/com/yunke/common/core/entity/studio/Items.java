package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 项目_任务表(Items)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_items")
public class Items implements Serializable {

  /**
   * 项目ID
   */
  @TableId(type = IdType.INPUT)
  private String itemsId;
  /**
   * 标题
   */
  private String title;
  /**
   * 简介
   */
  private String introduction;
  /**
   * 开始时间
   */
  private String startTime;
  /**
   * 结束时间
   */
  private String endTime;
  /**
   * 费用
   */
  private Object cost;
  /**
   * 发票
   */
  private String invoice;
  /**
   * 项目说明书
   */
  private String specification;
  /**
   * 源文件
   */
  private String url;
  /**
   * 状态(1:进行中/2:已完成)
   */
  private Integer state;
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