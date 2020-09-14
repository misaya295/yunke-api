package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("t_graduated_corporation")
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class GraduatedCorporation implements Serializable {

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private Long userId;
    /**
     * 公司名称
     */
    private String corporationName;

    /**
     * 职位
     */
    private String position;

    /**
     * 入职时间
     */
    private String entryTime;
    /**
     * 入职时间查询起始
     */
    @TableField(exist = false)
    private String entryTimeFrom;
    /**
     * 入职时间查询结尾
     */
    @TableField(exist = false)
    private String entryTimeTo;

    /**
     * 用户真实名称
     */
    @TableField(exist = false)
    private String fullName;


}