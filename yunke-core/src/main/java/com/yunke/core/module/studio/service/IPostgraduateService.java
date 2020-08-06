package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Postgraduate;

import java.util.Map;


/**
 * 考研人员表(Postgraduate)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface IPostgraduateService extends IService<Postgraduate> {

    /**
     * 添加考研
     *
     * @param postgraduate  添加考研的参数
     */
    void addPostgraduate(Postgraduate postgraduate);

    /**
     * 修改考研信息_PUT
     *
     * @param postgraduate  修改考研的参数
     */
    void updatePostgraduate(Postgraduate postgraduate);

    /**
     * 查询所有考研信息
     *
     * @param param
     * @param postgraduate
     * @return 考研分页信息列表
     */
    IPage<Postgraduate> postgraduateList(QueryParam param, Postgraduate postgraduate);

    /**
     *  通过id查询考研信息_GET
     *
     * @param id 考研id
     * @return  考研信息
     */
    Map getPostgraduate(String id);

    /**
     * 删除考研_DELETE
     *
     * @param ids 要删除的id
     */
    void deletePostgraduate(String[] ids);
}