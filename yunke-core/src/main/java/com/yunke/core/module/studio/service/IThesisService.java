package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.studio.Thesis;

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
     */
    void createTask(Thesis thesis);


}