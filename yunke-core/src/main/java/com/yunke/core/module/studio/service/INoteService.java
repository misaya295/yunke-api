package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Note;
import java.util.List;

/**
 * 备注表(Note)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface INoteService extends IService<Note> {

  /**
   * 分业查询备注数据
   *
   * @param param 分页数据
   * @param note  倍数模糊搜索数据
   * @return {@link IPage<Note>} 分页结果集
   */
  IPage<Note> pageNote(QueryParam param, Note note);

  /**
   * 获取备注集合
   *
   * @param note 备注数据
   * @return {@link List<Note>} 备注集合
   */
  List<Note> getList(Note note);


  /**
   * 现在备注信息
   *
   * @param note 备注数据
   */
  void createNote(Note note);

  /**
   * 删除备注数据
   *
   * @param noteIds 备注ID数组
   */
  void deleteNote(String[] noteIds);

  /**
   * 更新备注数据
   *
   * @param note 备注数据
   */
  void updateNote(Note note);


}