package com.yunke.core.module.studio.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.R;
import com.yunke.common.core.entity.studio.Members;
import com.yunke.common.core.entity.studio.Thesis;
import com.yunke.common.core.util.PageUtil;
import com.yunke.core.annotation.ControllerEndpoint;
import com.yunke.core.module.studio.service.IMembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 任务成员的中间表(Members)表控制层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("studio/members")
public class MembersController {

    private final IMembersService membersService;

    /**
     * 更新任务成员
     *
     * @param taskId 要更新的论文id
     * @param userId 要更新的成员ids
     */
    @PutMapping
    @PreAuthorize("hasAuthority('task:update')")
    @ControllerEndpoint(operation = "更新成员", exceptionMessage = "更新成员失败")
    public void updateMembers(String taskId, String userId) {
        String[] split = StrUtil.split(userId, StrUtil.COMMA);
        this.membersService.updateMembers(taskId, split);
    }


    /**
     * 更新任务负责人
     *
     * @param members 更新的数据
     */
    @PutMapping("charge")
    @PreAuthorize("hasAuthority('task:charge')")
    @ControllerEndpoint(operation = "更新负责人", exceptionMessage = "更新负责人失败")
    public void updateCharge(@Valid Members members) {
        this.membersService.updateCharge(members);
    }


    /**
     * 添加任务负责人
     *
     * @param members 添加的数据
     */
    @PostMapping("charge")
    @PreAuthorize("hasAuthority('task:charge')")
    @ControllerEndpoint(operation = "添加负责人", exceptionMessage = "添加负责人失败")
    public void addCharge(@Valid Members members) {
        this.membersService.updateCharge(members);
    }


    /**
     * 删除任务成员
     *
     * @param userId 要删除的任务成员
     * @param taskId 要删除的任务成员的论文
     */
    @DeleteMapping("{taskId}/{userId}")
    @PreAuthorize("hasAuthority('task:delete')")
    @ControllerEndpoint(operation = "删除负责人", exceptionMessage = "删除负责人失败")
    public void deleteMembers(@NotBlank(message = "{required}") @PathVariable String taskId, @NotBlank(message = "{required}") @PathVariable String userId) {
        String[] split = StrUtil.split(userId, StrUtil.COMMA);
        this.membersService.deleteMembers(taskId,split);
    }


    /**
     * 通过任务id查询成员列表
     *
     * @param param 分页信息
     * @param taskId 任务id
     * @return 成员列表
     */
    @GetMapping("{taskId}")
    public R<Map<String,Object>> membersList(QueryParam param,@NotBlank(message = "{required}") @PathVariable String taskId){
        IPage<Map<String,Object>> result = this.membersService.pageMembersList(param, taskId);
        return R.ok(PageUtil.toPage(result));
    }
}