package com.janson.performance.optimization.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则测试示例
 * @Author: Janson
 * @Date: 2020/8/27 19:37
 **/
public class RegexTest {

    public static void main(String[] args) {
        testGreedy();
        System.out.println("------------------");
        testGreedy1();
        System.out.println("=================");
        testReluctant();
        System.out.println("///////////////////////////////");
        testPossessive();
    }

    public static void testGreedy() {
        String test = "abbc";
        String regex = "ab{1,3}c";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(test);
        boolean flag = matcher.matches();
        if (flag) {
            for (int i = matcher.groupCount(); i >= 1; i--) {
                String groupI = matcher.group(i);
                System.out.println("i:" + i + " groupI:" + groupI);
            }
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }

    public static void testGreedy1() {
        String test = "abbbc";
        String regex = "ab{1,3}c";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(test);
        boolean flag = matcher.matches();
        if (flag) {
            for (int i = matcher.groupCount(); i >= 1; i--) {
                String groupI = matcher.group(i);
                System.out.println("i:" + i + " groupI:" + groupI);
            }
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }

    public static void testReluctant() {
        String test = "abc";
        String regex = "ab{1,3}?c";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(test);
        boolean flag = matcher.matches();
        if (flag) {
            for (int i = matcher.groupCount(); i >= 1; i--) {
                String groupI = matcher.group(i);
                System.out.println("i:" + i + " groupI:" + groupI);
            }
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }


    public static void testPossessive() {
        String test = "abbc";
        String regex = "ab{1,3}+c";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(test);
        boolean flag = matcher.matches();
        if (flag) {
            for (int i = matcher.groupCount(); i >= 1; i--) {
                String groupI = matcher.group(i);
                System.out.println("i:" + i + " groupI:" + groupI);
            }
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }


}
