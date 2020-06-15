package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Note;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.module.studio.service.INoteService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 备注表(Note)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/note")
public class NoteController {

  private final INoteService noteService;

  @GetMapping("options")
  public R<List<Note>> optionsNote(Note note) {
    return R.ok(noteService.getList(note));
  }

  @GetMapping
  @PreAuthorize("hasAuthority('note:view')")
  public R<Map<String, Object>> pageNote(QueryParam param, Note note) {
    IPage<Note> pageResult = this.noteService.pageNote(param, note);
    return R.ok(PageUtil.toPage(pageResult));
  }

  @PostMapping
  @PreAuthorize("hasAuthority('note:add')")
  public void addNote(@Valid Note note) {
    this.noteService.createNote(note);
  }

  @PutMapping
  @PreAuthorize("hasAuthority('note:update')")
  public void updateNote(@Valid Note note) {
    this.noteService.updateNote(note);
  }

  @DeleteMapping("{noteIds}")
  @PreAuthorize("hasAuthority('note:delete')")
  public void deleteNote(@NotBlank(message = "{required}") @PathVariable String noteIds) {
    String[] ids = noteIds.split(StrUtil.COMMA);
    this.noteService.deleteNote(ids);
  }

}