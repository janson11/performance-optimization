package com.janson.performance.optimization.user;

import com.github.houbb.heaven.util.lang.ObjectUtil;

/**
 * @Description: 自定义脱敏工具类
 * @Author: Janson
 * @Date: 2020/12/9 9:25
 **/
public class CustomSensitiveStrategyUtil {

    private CustomSensitiveStrategyUtil() {
        // do nothing
    }

    /**
     * 脱敏电话号码
     *
     * @param phone 电话号码
     * @return 结果
     */
    public static String phone(final String phone) {
        final int prefixLength = 3;
        final String middle = "******";
        return buildString(phone, middle, prefixLength);
    }

    /**
     * 构建新的字符串
     *
     * @param original     原始对象
     * @param middle       中间隐藏信息
     * @param prefixLength 前边信息长度
     * @return 构建后的新字符串
     * @since 0.0.8
     */
    private static String buildString(final Object original,
                                      final String middle,
                                      final int prefixLength) {
        if (ObjectUtil.isNull(original)) {
            return null;
        }

        final String string = original.toString();
        final int stringLength = string.length();

        String prefix = "";
        String suffix = "";

        if (stringLength >= prefixLength) {
            prefix = string.substring(0, prefixLength);
        } else {
            prefix = string.substring(0, stringLength);
        }

        int suffixLength = stringLength - prefix.length() - middle.length();
        if (suffixLength > 0) {
            suffix = string.substring(stringLength - suffixLength);
        }

        return prefix + middle + suffix;
    }
}
