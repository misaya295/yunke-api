package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("t_graduated_corporation")
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
     * 用户真实名称
     */
    @TableField(exist = false)
    private String fullName;

    public GraduatedCorporation() {
    }

    public GraduatedCorporation(Long userId, String corporationName) {
        this.userId = userId;
        this.corporationName = corporationName;
    }

}