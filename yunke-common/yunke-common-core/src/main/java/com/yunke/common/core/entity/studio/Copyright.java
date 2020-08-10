package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 软件著作权_任务表(Copyright)表实体类1
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_copyright")
public class Copyright implements Serializable {

  /**
   * 软件著作权ID
   */
  @TableId(type = IdType.INPUT)
  private String copyrightId;
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
   * 证书
   */
  private String certificate;
  /**
   * 申请书
   */
  private String applicationForm;
  /**
   * 源文件
   */
  private String originFile;
  /**
   * 软件协议
   */
  private String agreement;
  /**
   * 费用
   */
  private Object cost;
  /**
   * 发票
   */
  private String invoice;
  /**
   * 项目ID
   */
  private String itemId;
  /**
   * 状态(1:进行中/2:已完成/3:申报中)
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
   * 成员id ","分隔
   */
  @TableField(exist = false)
  private String userId;
  /**
   * 成员角色 ","分隔   1为负责人，2为成员，3为指导老师
   */
  @TableField(exist = false)
  private String  m_state;

}
