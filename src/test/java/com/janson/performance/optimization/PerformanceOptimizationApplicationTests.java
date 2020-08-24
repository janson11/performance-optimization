package com.janson.performance.optimization;


import org.junit.Test;
import org.springframework.util.Assert;


public class PerformanceOptimizationApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void stringTest() {
        String str1 = "abc";
        String str2 = new String("abc");
        Assert.isTrue(str1 == str2, "str1是否等于str2");
    }

    @Test
    public void stringTest1() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        Assert.isTrue(str1 == str3, "str1是否等于str3");
    }

    @Test
    public void stringTest2() {
        String str2 = new String("abc");
        String str3 = str2.intern();
        Assert.isTrue(str2 == str3, "str2是否等于str3");
    }

    @Test
    public void test() {
        String str = "abcdef";
        StringBuffer stringBuffer = new StringBuffer("abcedf");
        for (int i = 0; i < 10000; i++) {
//            str = str + i;
            str = stringBuffer.append(i).toString();
            if (i == 9999) {
                System.out.println(str.length());
            }
        }
    }

}
