package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 比赛人员奖项表(MatchMemberAwards)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_match_member_awards")
public class MatchMemberAwards implements Serializable {

  /**
   * 自增id
   */
  @TableId(type = IdType.AUTO)
  private Integer id;
  /**
   * 用户id(为空是团队赛，团队奖)
   */
  private Integer userId;
  /**
   * 奖项(1：一等，2：二等，3：三等，4：特等，5：优胜，6、无)
   */
  private Integer rankCode;
  /**
   * 关联 比赛ID
   */
  private String taskId;
  /**
   * 奖状
   */
  private String certificate;


}
