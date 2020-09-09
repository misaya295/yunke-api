package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Thesis;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.common.core.util.PageUtil;
import com.yunke.common.core.util.UserIdUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IThesisService;
import com.yunke.core.module.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 论文_任务表(Thesis)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:57
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/thesis")
public class ThesisController {

    private final IThesisService thesisService;
    private final IUserService userService;

        /**
         * 新增论文任务_POST
         *
         * @param thesis  论文_任务 新增参数
         */
        @PostMapping
        @PreAuthorize("hasAuthority('task:add')")
        @ControllerEndpoint(operation = "新增任务", exceptionMessage = "新增任务失败")
        public void addTask(@Valid Thesis thesis) {
            String[] split_userId = StrUtil.split(thesis.getUserId(), StrUtil.COMMA); //成员id
            String[] split_state = StrUtil.split(thesis.getM_state(), StrUtil.COMMA);//成员角色
            List<Integer> strangerIndex = UserIdUtil.formatStrangerName(split_userId);//非内部成员id的下标集合
            //给非内部人员注册一个禁用状态的账号
            for(int i=0;i<strangerIndex.size();i++) {
                SystemUser user = new SystemUser();
                user.setUsername(split_userId[strangerIndex.get(i)]);//用户输入的名字为userName
                user.setFullName(split_userId[strangerIndex.get(i)]);//用户输入的名字
                user.setStatus("0");//默认禁用
                user.setCreateTime(new Date());//默认当前时间
                user.setDeptId((long)47);
                userService.createUser(user);
                //将原本的非内置成员的不正常id替换为创建后的id
                split_userId[strangerIndex.get(i)] = userService.getSystemUser(user.getUsername()).getUserId().toString();
            }
            this.thesisService.createTask(thesis, split_userId, split_state);
        }

        /**
         * 删除论文任务_DELETE
         *
         * @param thesisIds 要删除任务的id
         */
        @DeleteMapping("{thesisIds}")
        @PreAuthorize("hasAuthority('task:delete')")
        @ControllerEndpoint(operation = "删除任务", exceptionMessage = "删除任务失败")
        public void deleteTask(@NotBlank(message = "{required}") @PathVariable String thesisIds) {
            String[] ids = thesisIds.split(StrUtil.COMMA);
            this.thesisService.deleteTask(ids);
        }

        /**
         * 更新论文任务_PUT
         *
         * @param thesis 要更新的对象
         */
        @PutMapping
        @PreAuthorize("hasAuthority('task:update')")
        @ControllerEndpoint(operation = "更新任务", exceptionMessage = "更新任务失败")
        public void updateTask(@Valid Thesis thesis) {
            this.thesisService.updateTask(thesis);
        }


        /**
         * 查询论文任务列表_GET
         *
         * @param param  分页数据 (pageNum,pageSize,title,ascending/descending)
         * @param thesis 模糊查询的对象 (title) ,条件待定
         * @return Page ， Thesis（title，thesisId）
         */
        @GetMapping
        public R<Map<String, Object>> taskList(QueryParam param, Thesis thesis) {
            IPage<Thesis> result = this.thesisService.pageTaskList(param, thesis);
            return R.ok(PageUtil.toPage(result));
        }

        /**
         * 查询论文任务_GET
         *
         * @param thesisId 论文id
         * @return thesis 论文详细信息
         */
        @GetMapping("{thesisId}")
        public R<Thesis> getTask(@NotBlank(message = "{required}") @PathVariable String thesisId) {
            Thesis thesis = this.thesisService.getTask(thesisId);
            return R.ok(thesis);
        }

        /**
         * 论文任务总条数_GET
         *
         * @return 论文任务总条数
         */
        @GetMapping("count")
        public R<Integer> getTaskCount() {
        return R.ok(this.thesisService.getAllTaskCount());
    }


        /**
         * 修改任务状态
         *
         * @param thesis
         */
        @PutMapping("state")
        @PreAuthorize("hasAuthority('task:update')")
        @ControllerEndpoint(operation = "更新任务状态", exceptionMessage = "更新任务状态")
        public void updateState(@Valid Thesis thesis) {
            this.thesisService.updateState(thesis);
        }

}