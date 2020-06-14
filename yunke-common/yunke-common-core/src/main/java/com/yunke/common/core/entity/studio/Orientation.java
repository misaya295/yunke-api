package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 方向表(Orientation)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_orientation")
public class Orientation implements Serializable {

  /**
   * 方向ID
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 方向名
   */
  private String orientation;


}