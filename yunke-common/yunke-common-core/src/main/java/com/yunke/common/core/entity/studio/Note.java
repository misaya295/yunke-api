package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 备注表(Note)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_note")
public class Note implements Serializable {

  /**
   * 备注主键
   */
  @TableId(type = IdType.AUTO)
  private Integer noteId;
  /**
   * 备注类型
   */
  private String noteName;


}