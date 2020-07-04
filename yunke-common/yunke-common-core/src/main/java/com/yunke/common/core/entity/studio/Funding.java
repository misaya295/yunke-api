package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 经费表(Funding)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_funding")
public class Funding implements Serializable {

  /**
   * 自增id
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 审核人ID
   */
  private Integer verifierId;
  /**
   * 证明人ID
   */
  private Integer certifierId;
  /**
   * 名称
   */
  private String name;
  /**
   * 类型
   */
  private String type;
  /**
   * 申请时间
   */
  private String applyTime;
  /**
   * 报销成功时间
   */
  private String successTime;
  /**
   * 发票
   */
  private String invoice;
  /**
   * 费用
   */
  private Double cost;
  /**
   * 银行卡号
   */
  private String card;
  /**
   * 申请人的ID
   */
  private Integer proposerId;
  /**
   * 申请状态(1申请中/2报销中/3报销成功/4申请失败)
   */
  private Integer state;
  /**
   * 对应的任务UUID
   */
  private String taskId;


}