package com.yunke.common.core.util;

import java.util.ArrayList;
import java.util.List;

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
        String fomrmat = "[//d]*";
        for(int i=0;i<userIds.length;i++){
            if(!fomrmat.matches(userIds[i])){
                strangerIndex.add(i);
            }
        }
        return strangerIndex;
    }
}
