package com.janson.performance.optimization.user;

import com.github.houbb.sensitive.core.api.SensitiveUtil;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/11/23 10:08
 **/
public class UserTest {

    public static void main(String[] args) {
        User user = buildUser();
        System.out.println("脱敏前原始： " + user);
        User sensitiveUser = SensitiveUtil.desCopy(user);
        System.out.println("脱敏对象： " + sensitiveUser);
        System.out.println("脱敏后原始： " + user);
    }


    private static User buildUser() {
        User user = new User();
        user.setUsername("脱敏君");
        user.setPassword("123456");
        user.setEmail("12345@qq.com");
        user.setIdCard("123456190001011234");
        user.setPhone("18888888888");
        return user;
    }
}
