package com.janson.performance.optimization.user;

import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.sensitive.api.IContext;
import com.github.houbb.sensitive.api.IStrategy;

/**
 * @Description: 自定义手机号码过敏规则
 * 脱敏规则：139******31
 * @Author: Janson
 * @Date: 2020/12/9 9:24
 **/
public class CustomStrategyPhone implements IStrategy {

    @Override
    public Object des(Object original, IContext context) {
        return CustomSensitiveStrategyUtil.phone(ObjectUtil.objectToString(original));
    }
}
