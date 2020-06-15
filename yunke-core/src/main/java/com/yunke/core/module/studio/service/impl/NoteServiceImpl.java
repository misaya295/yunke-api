package com.yunke.core.module.studio.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Note;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.common.core.exception.ApiException;
import com.yunke.core.module.studio.mapper.NoteMapper;
import com.yunke.core.module.studio.service.INoteService;
import com.yunke.core.module.system.service.IUserService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 备注表(Note)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements INoteService {

  private final IUserService userService;

  @Override
  public IPage<Note> pageNote(QueryParam param, Note note) {
    return baseMapper
        .selectPage(new Page<>(param.getPageNum(), param.getPageSize()), checkCondition(note));
  }

  @Override
  public List<Note> getList(Note note) {
    return baseMapper.selectList(checkCondition(note));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void createNote(Note note) {
    baseMapper.insert(note);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteNote(String[] noteIds) {
    if (hasSystemUser(noteIds)) {
      throw new ApiException("删除的备注信息包含相关用户，请重试");
    }
    baseMapper.deleteBatchIds(Arrays.asList(noteIds));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateNote(Note note) {
    baseMapper.updateById(note);
  }

  /**
   * 条件判断
   *
   * @param note note 对象
   * @return {@link LambdaQueryWrapper<Note>} wrapper 对象
   */
  private LambdaQueryWrapper<Note> checkCondition(Note note) {
    LambdaQueryWrapper<Note> wrapper = new LambdaQueryWrapper<>();
    if (note.getNoteId() != null) {
      wrapper.eq(Note::getNoteId, note.getNoteId());
    }
    if (StrUtil.isNotBlank(note.getNoteName())) {
      wrapper.like(Note::getNoteName, note.getNoteName());
    }
    return wrapper;
  }

  private boolean hasSystemUser(String[] noteIds) {
    return userService.count(
        new LambdaQueryWrapper<SystemUser>().in(SystemUser::getNoteId, Arrays.asList(noteIds))) > 0;
  }
}