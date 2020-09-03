package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学校资产表 (SchoolAssets)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_school_assets")
public class SchoolAssets implements Serializable {

  /**
   * 学校资产ID
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 收录日期
   */
  private String inclusionDate;
  /**
   * 资产名称
   */
  private String assetsName;
  /**
   * 资产编号
   */
  private String assetsNum;
  /**
   * 资产价格
   */
  private Object price;
  /**
   * 报废日期
   */
  private String scrapDate;
  /**
   * 报废信息
   */
  private String scrapDetail;
  /**
   * 报废申请人id
   */
  private String scrapApplicantId;
  /**
   * 报废申请人名字
   */
  private String scrapApplicantName;
}