package com.janson.performance.optimization;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class PerformanceOptimizationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void stringTest() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        Assert.isTrue(str1 == str2, "str1是否等于str2");
        Assert.isTrue(str1 == str3, "str1是否等于str3");
        Assert.isTrue(str1 == str3, "str1是否等于str3");
    }

}
