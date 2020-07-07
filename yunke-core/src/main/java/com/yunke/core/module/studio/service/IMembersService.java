package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.studio.Members;

import java.util.List;

/**
 * 任务成员的中间表(Members)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface IMembersService extends IService<Members> {

    /**
     * 更新任务成员
     * @param taskId  要更新的论文id
     * @param userId  要更新的成员ids
     */
    void updateMembers(String taskId, String[] userId);

    /**
     * 更新任务负责人
     * @param members  要更新的负责人
     *
     */
    void updateCharge(Members members);

    /**
     * 删除任务成员
     * @param userId 要删除的任务成员
     * @param taskId 要删除的任务成员的论文
     */
    void deleteMembers(String taskId, String[] userId);
}