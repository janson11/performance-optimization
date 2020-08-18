package com.janson.performance.optimization.string;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 字符串示例
 * @Author: Janson
 * @Date: 2020/8/18 23:49
 **/
@Slf4j
public class StringDemo {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        log.info("str1是否等于str2:{}", (str1 == str2));
        log.info("str2是否等于str3:{}", (str1 == str3));
        log.info("str1是否等于str3:{}", (str1 == str3));
    }

}
