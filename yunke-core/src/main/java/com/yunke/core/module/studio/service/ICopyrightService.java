package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Copyright;
import com.yunke.common.core.entity.studio.Thesis;

import java.util.Map;

/**
 * 软件著作权_任务表(Copyright)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface ICopyrightService extends IService<Copyright> {

    /**
     * 新增软件著作权任务
     *
     * @param copyright 软件著作权任务对象
     * @param userId 成员id
     * @param state  成员角色
     */
    void createTask(Copyright copyright, String[] userId, String[] state);

    /**
     * 删除软件著作权任务
     *
     * @param ids 要删除对象的ids
     */
    void deleteTask(String[] ids);

    /**
     * 更新软件著作权任务
     *
     * @param copyright 要更新的软件著作权对象
     */
    void updateTask(Copyright copyright);

    /**
     * 查询软件著作权任务列表
     *
     * @param param 分页数据
     * @param copyright 条件查询的软件著作权对象
     */
    IPage<Copyright> pageTaskList(QueryParam param, Copyright copyright);

    /**
     * id查询软件著作权任务
     *
     * @param copyrightId 软件著作权id
     */
    Map<String,Object> getTask(String copyrightId);

    /**
     * 查询所有项目条数
     * @return 返回所有项目条数
     */
    Integer getAllTaskCount();
}