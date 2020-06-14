package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 比赛_任务表(Match)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:01
 */
@Data
@TableName("t_match")
public class Match implements Serializable {

  /**
   * 比赛ID
   */
  @TableId(type = IdType.INPUT)
  private String matchUuid;
  /**
   * 比赛名称
   */
  private String title;
  /**
   * 比赛等级(0:国家级/1:省级/2:校级)
   */
  private Integer level;
  /**
   * 比赛类型（0:个人/1:团队）
   */
  private Integer type;
  /**
   * 申请书
   */
  private String applicationForm;
  /**
   * 比赛时间
   */
  private String time;
  /**
   * 费用
   */
  private Object cost;
  /**
   * 发票
   */
  private String invoice;
  /**
   * 证书
   */
  private String certificate;
  /**
   * 状态(1:进行中/2:已结束)
   */
  private Integer state;
  /**
   * 是否已报销（0否/1是）
   */
  private Integer reimbursement;


}