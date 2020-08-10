package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Thesis;
import java.util.Map;

/**
 * 论文_任务表(Thesis)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface IThesisService extends IService<Thesis> {

    /**
     * 新增论文任务
     *
     * @param thesis 论文任务对象
     * @param userId 成员id
     * @param state  成员角色
     */
    void createTask(Thesis thesis, String[] userId, String[] state);

    /**
     * 删除论文任务
     *
     * @param ids 要删除对象的ids
     */
    void deleteTask(String[] ids);

    /**
     * 更新论文任务
     *
     * @param thesis 要更新的论文对象
     */
    void updateTask(Thesis thesis);

    /**
     * 查询论文任务列表
     *
     * @param param 分页数据
     * @param thesis 条件查询的论文对象
     */
    IPage<Thesis> pageTaskList(QueryParam param, Thesis thesis);

    /**
     * id查询论文任务
     *
     * @param thesisId 论文id
     */
    Map<String,Object> getTask(String thesisId);

    /**
     * 查询所有项目条数
     * @return 返回所有项目条数
     */
    Integer getAllTaskCount();


}