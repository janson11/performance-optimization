package com.janson.performance.optimization.design;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/12/8 20:04
 **/
@Slf4j
public class Test {
    public static void main(String[] args) {

        log.info("{}", User.getInstance());
        log.info("{}", User.getInstance());
        log.info("{}", User.getInstance() == User.getInstance());

    }
}
