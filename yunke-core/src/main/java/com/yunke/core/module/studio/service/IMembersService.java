package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Members;

import java.util.List;
import java.util.Map;

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

    /**
     * 通过任务id查询成员列表
     *
     * @param param 分页信息
     * @param taskId 任务id
     * @return 成员列表
     */
    IPage<Map<String, Object>> pageMembersList(QueryParam param, String taskId);


    /**
     * 通过任务id查询成员列表
     *
     * @param taskId 任务id
     * @return
     */
    List<Members> getMembers(String taskId);
}