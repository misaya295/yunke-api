package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 资格证表(Certificate)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_certificate")
public class Certificate implements Serializable {

  /**
   * 考证ID
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 考证人ID
   */
  private Integer userId;
  /**
   * 类型
   */
  private String type;
  /**
   * 证书名称
   */
  private String title;
  /**
   * 费用
   */
  private Object cost;
  /**
   * 考证时间
   */
  private String time;
  /**
   * 发票
   */
  private String invoice;
  /**
   * 证书
   */
  private String certificate;
  /**
   * 通过状态(0:失败/1:成功)
   */
  private Integer success;
  /**
   * 是否已报销（0否/1是）
   */
  private Integer reimbursement;
  /**
   * 状态（0正在考取/1已完成）
   */
  private Integer state;
  /**
   * 姓名
   */
  @TableField(exist = false)
  private String fullName;


}