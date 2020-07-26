package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 维修信息表 (SchoolAssetsRepair)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_school_assets_repair")
public class SchoolAssetsRepair implements Serializable {

  /**
   * 维修信息ID
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 维修日期
   */
  private String repairDate;
  /**
   * 学校资产ID
   */
  private Integer assetsName;
  /**
   * 维修价格
   */
  private Double repairPrice;
  /**
   * 发票
   */
  private String repairInvoice;
  /**
   * 维修证明人【userIndoUuid】
   */
  private Integer repairProverUserInfoUuid;


  /**
   * 资产名称
   */
  @TableField(exist = false)
  private String propertyName;

  /**
   * 维修证明人名称
   */
  @TableField(exist = false)
  private String repairProverUserInfoName;

}