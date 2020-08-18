package com.yunke.common.core.entity.studio;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 任务成员的中间表(Members)表实体类
 *
 * @author chachae
 * @since 2020-06-14 14:09:04
 */
@Data
@TableName("t_members")
public class Members implements Serializable {

    /**
     * 成员ID
     */
    @TableId(type = IdType.INPUT)
    private Integer userId;
    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 1为负责人，2为成员，3为指导老师
     */
    private Integer state;

    /**
     * 成员姓名
     */
    @TableField(exist = false)
    private String fullName;

    public Members(){}

    public Members(int userId, int state, String taskId) {
        this.userId = userId;
        this.state = state;
        this.taskId = taskId;
    }
}