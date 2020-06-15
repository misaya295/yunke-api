package com.yunke.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户数据权限关联表(TUserDept)表实体类
 *
 * @author chachae
 * @since 2020-05-28 12:03:36
 */
@Data
@NoArgsConstructor
@TableName("t_user_data_permission")
public class UserDataPermission implements Serializable {

  private static final long serialVersionUID = -6330896351089751532L;

  @TableId(type = IdType.INPUT)
  private Long userId;

  private Long deptId;

  public UserDataPermission(Long userId, Long deptId) {
    this.userId = userId;
    this.deptId = deptId;
  }

  public UserDataPermission(Long userId) {
    this(userId, null);
  }

}