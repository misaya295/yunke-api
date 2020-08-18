package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Items;

import java.util.Map;

/**
 * 项目_任务表(Items)表服务接口1
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface IItemsService extends IService<Items> {

    /**
     * 新增项目任务
     *
     * @param items 项目任务对象
     * @param userId 成员id
     * @param state  成员角色
     */
    void createTask(Items items, String[] userId, String[] state);

    /**
     * 删除项目任务
     *
     * @param ids 要删除对象的ids
     */
    void deleteTask(String[] ids);

    /**
     * 更新项目任务
     *
     * @param items 要更新的论文对象
     */
    void updateTask(Items items);

    /**
     * 查询项目任务列表
     *
     * @param param 分页数据
     * @param items 条件查询的项目对象
     */
    IPage<Items> pageTaskList(QueryParam param, Items items);

    /**
     * id查询项目任务
     *
     * @param itemsId 项目id
     */
    Items getTask(String itemsId);

    /**
     * 查询所有项目条数
     * @return 返回所有项目条数
     */
    Integer getAllTaskCount();

    /**
     * 修改任务状态
     *
     * @param items
     */
    void updateState(Items items);
}