package com.ms.blog.common;

import com.ms.blog.entity.User;

/**
 * 用户信息本地变量
 * @author wyh
 * @date 2023/02/01 15:53
 */
public class UserThreadLocal {
    private UserThreadLocal(){}

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void  put(User user){
        LOCAL.set(user);
    }

    public static User get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
