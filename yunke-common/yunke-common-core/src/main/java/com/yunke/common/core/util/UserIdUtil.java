package com.yunke.common.core.util;

import com.yunke.common.core.entity.system.SystemUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * userId工具类
 *
 * @author Pning
 * @since 2020/9/9 9:21
 */
public class UserIdUtil {

    private UserIdUtil(){
    };

    /**
     * 返回集合中不为为userId的数据下标集合
     * @param userIds
     * @return 返回不是userId的字符串集合
     */
    public static List<Integer> formatStrangerName(String[] userIds) {
        List<Integer> strangerIndex = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]*");
        for(int i=0;i<userIds.length;i++) {
            if(!pattern.matcher(userIds[i]).matches()) {
                strangerIndex.add(i);
            }
        }
        System.out.println(strangerIndex.toString());
        return strangerIndex;
    }

    /**
     * 通过用户名给非内部人员生成一个用户对象
     * @param userName
     * @return SystemUser对象
     */
    public static SystemUser strangerUser(String userName) {
        SystemUser user = new SystemUser();
        user.setUsername(new Date().toString()+ (new Random()).nextInt(10)%(10-1+1) + 1);//用户输入的时间为userName+1-9的随机数
        user.setFullName(userName);//用户输入的名字
        user.setStatus("0");//默认禁用
        user.setCreateTime(new Date());//默认当前时间
        user.setDeptId((long)47);//默认后端
        user.setDeptIds("1,2,3,4,10,47,48,49");//可以获取全部门的属性,防止到时候不能显示名字
        user.setRoleId("7");//默认学生
        return user;
    }
}
