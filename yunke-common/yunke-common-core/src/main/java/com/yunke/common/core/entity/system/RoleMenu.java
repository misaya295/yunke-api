package com.yunke.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author chachae
 */
@TableName("t_role_menu")
@Data
public class RoleMenu implements Serializable {

  private static final long serialVersionUID = -7573904024872252113L;

  @TableId(type = IdType.INPUT)
  private Long roleId;

  private Long menuId;
}