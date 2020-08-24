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
//        testStr();
//        test1();
//        test2();
//        test3();
        test4();
    }


    private static void test4() {
        String test = "大幅度1发,sss,ereee,riririr,0ldkdmmm,ss,df,sssss43";
        String[] split = test.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println("---------------");
        int i = test.indexOf(",");
        System.out.println(i);

    }

    private static void test3() {
//        String a = new String("abc").intern();
//        String b = new String("abc").intern();
        String c = new String("abc");
        String d = new String("abc");
/*        if (a == b) {
            System.out.println("a==b");
        }*/
        if (c == d) {
            System.out.println("c==d");
        }

    }

    private static void test2() {
        String str = "abcdef";
        for (int i = 0; i < 10000; i++) {
            str = str + i;
            System.out.println(str);
        }
    }

    private static void test1() {
        String s = "ab" + "cd" + "ef";
        System.out.println(s);
    }


    private static void testStr() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        log.info("str1是否等于str2:{}", (str1 == str2));
        log.info("str2是否等于str3:{}", (str2 == str3));
        log.info("str1是否等于str3:{}", (str1 == str3));
    }

}
