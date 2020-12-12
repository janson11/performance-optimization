package com.janson.performance.optimization.user;

import com.github.houbb.sensitive.annotation.Sensitive;
import com.github.houbb.sensitive.core.api.strategory.StrategyCardId;
import com.github.houbb.sensitive.core.api.strategory.StrategyChineseName;
import com.github.houbb.sensitive.core.api.strategory.StrategyEmail;
import com.github.houbb.sensitive.core.api.strategory.StrategyPassword;
import lombok.Data;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/11/23 10:00
 **/
@Data
public class User {
    @Sensitive(strategy = StrategyChineseName.class)
    private String username;

    @Sensitive(strategy = StrategyCardId.class)
    private String idCard;

    @Sensitive(strategy = StrategyPassword.class)
    private String password;

    @Sensitive(strategy = StrategyEmail.class)
    private String email;

    @Sensitive(strategy = CustomStrategyPhone.class)
    private String phone;


}
