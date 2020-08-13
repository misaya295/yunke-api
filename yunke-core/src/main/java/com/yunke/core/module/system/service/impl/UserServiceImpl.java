package com.yunke.core.module.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.OptionTree;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.auth.CurrentUser;
import com.yunke.common.core.entity.system.SystemUser;
import com.yunke.common.core.entity.system.UserDataPermission;
import com.yunke.common.core.entity.system.UserRole;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SecurityUtil;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.system.mapper.UserMapper;
import com.yunke.core.module.system.service.IUserDataPermissionService;
import com.yunke.core.module.system.service.IUserRoleService;
import com.yunke.core.module.system.service.IUserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户业务接口
 *
 * @author chachae
 * @since 2020/4/30 19:56
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

  private final IUserRoleService userRoleService;
  private final PasswordEncoder passwordEncoder;
  private final IUserDataPermissionService userDataPermissionService;

  @Override
  public SystemUser getSystemUser(String username) {
    LambdaQueryWrapper<SystemUser> qw = new LambdaQueryWrapper<>();
    qw.eq(SystemUser::getUsername, username);
    return getOne(qw);
  }

  @Override
  public IPage<SystemUser> pageSystemUser(QueryParam param, SystemUser user) {
    Page<SystemUser> page = new Page<>(param.getPageNum(), param.getPageSize());
    SortUtil.handlePageSort(param, page, "username", SystemConstant.ORDER_ASC, true);
    return baseMapper.pageSystemUserDetail(page, user);
  }

  @Override
  public void updateLoginTime(String username) {
    LambdaQueryWrapper<SystemUser> qw = new LambdaQueryWrapper<>();
    qw.eq(SystemUser::getUsername, username);
    baseMapper.update(new SystemUser().setLastLoginTime(new Date()), qw);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void createUser(SystemUser user) {
    // 创建用户
    user
        .setCreateTime(new Date())
        .setAvatar(SystemUser.DEFAULT_AVATAR)
        .setPassword(passwordEncoder.encode(SystemUser.DEFAULT_PASSWORD));
    save(user);
    // 保存用户角色
    String[] roles = StrUtil.splitToArray(user.getRoleId(), StrUtil.C_COMMA);
    setUserRoles(user, roles);
    // 保存用户数据权限关联关系
    String[] deptIds = StrUtil.splitToArray(user.getDeptIds(), StrUtil.C_COMMA);
    setUserDataPermissions(user, deptIds);
  }

  @Override
  public void deleteUsers(String[] userIds) {
    // 判断是否删除数据内包含当前用户
    if (hasCurrentUser(userIds)) {
      throw new ApiException("不能删除当前用户");
    }
    List<String> list = Arrays.asList(userIds);
    removeByIds(list);
    // 删除用户角色
    this.userRoleService.deleteUserRolesByUserId(userIds);
    this.userDataPermissionService.deleteByUserIds(userIds);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateUser(SystemUser user) {
    // 更新用户
    user
        .setPassword(null)
        .setUsername(null)
        .setCreateTime(null)
        .setUpdateTime(new Date());
    updateById(user);
    // 维护用户角色信息
    String[] userIds = {String.valueOf(user.getUserId())};
    userRoleService.deleteUserRolesByUserId(userIds);
    String[] roles = StrUtil.split(user.getRoleId(), StrUtil.COMMA);
    setUserRoles(user, roles);
    // 维护用户数据权限信息
    userDataPermissionService.deleteByUserIds(userIds);
    String[] deptIds = StrUtil.split(user.getDeptIds(), StrUtil.COMMA);
    setUserDataPermissions(user, deptIds);
  }

  @Override
  public List<Long> getUserIdByDeptIds(String[] deptIds) {
    return Lists.transform(baseMapper.selectList(
        new LambdaQueryWrapper<SystemUser>()
            .in(SystemUser::getDeptId, Arrays.asList(deptIds))), SystemUser::getUserId);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateProfile(SystemUser user) {
    user.setPassword(null).setUsername(null).setStatus(null);
    if (hasCurrentUser(new String[]{String.valueOf(user.getUserId())})) {
      updateById(user);
    } else {
      throw new ApiException("您无权修改别人的账号信息！");
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateAvatar(String avatar) {
    SystemUser user = new SystemUser().setAvatar(avatar);
    String curUsername = SecurityUtil.getCurrentUsername();
    this.baseMapper.update(user,
        new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUsername, curUsername));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updatePassword(String password) {
    SystemUser user = new SystemUser().setPassword(passwordEncoder.encode(password));
    String curUsername = SecurityUtil.getCurrentUsername();
    baseMapper.update(user,
        new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getUsername, curUsername));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void resetPassword(String[] usernames) {
    SystemUser params = new SystemUser();
    params.setPassword(passwordEncoder.encode(SystemUser.DEFAULT_PASSWORD));

    baseMapper
        .update(params, new LambdaQueryWrapper<SystemUser>()
            .in(SystemUser::getUsername, Arrays.asList(usernames)));
  }

  @Override
  public List<OptionTree<SystemUser>> getSystemUserTree(SystemUser user) {
    List<SystemUser> users = baseMapper.selectSystemUserDetail(user);
    Collection<List<SystemUser>> values = users.stream()
        .collect(Collectors.groupingBy(SystemUser::getDeptId)).values();

    List<OptionTree<SystemUser>> result = new ArrayList<>(values.size());
    for (List<SystemUser> curs : values) {
      List<OptionTree<SystemUser>> trees = new ArrayList<>(curs.size());
      curs.forEach(cur -> trees.add(new OptionTree<>(cur.getUserId(), cur.getFullName())));
      result.add(new OptionTree<>(curs.get(0).getDeptId(), curs.get(0).getDeptName(), trees));
    }

    return result;
  }

  private void setUserRoles(SystemUser user, String[] roles) {
    List<UserRole> userRoles = new ArrayList<>(roles.length);
    Stream.of(roles).forEach(id -> userRoles.add(new UserRole(user.getUserId(), Long.valueOf(id))));
    userRoleService.saveBatch(userRoles);
  }

  private void setUserDataPermissions(SystemUser user, String[] deptIds) {
    List<UserDataPermission> udps = new ArrayList<>(deptIds.length);
    Stream.of(deptIds)
        .forEach(id -> udps.add(new UserDataPermission(user.getUserId(), Long.valueOf(id))));
    userDataPermissionService.saveBatch(udps);
  }

  private boolean hasCurrentUser(String[] userIds) {
    CurrentUser cur = SecurityUtil.getCurrentUser();
    if (cur != null) {
      for (String userId : userIds) {
        if (String.valueOf(cur.getUserId()).equals(userId)) {
          return true;
        }
      }
    }
    return false;
  }
}
