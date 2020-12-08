package com.janson.performance.optimization.design;

/**
 * @Description: 完整的枚举单例
 * @Author: Janson
 * @Date: 2020/12/8 19:56
 **/
public class User {

    /**
     * 私有化构造器
     */
    private User() {
    }

    enum SingletonEnum {
        /**
         * 创建一个枚举对象，该对象天生为单例
         */
        INSTANCE;

        private User user;

        /**
         * 私有化枚举的构造函数
         */
        SingletonEnum() {
            user = new User();
        }

        public User getInstance() {
            return user;
        }
    }

    /**
     * 对外暴露一个获取User对象的静态方法。
     *
     * @return 用户实例
     */
    public static User getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }


}
