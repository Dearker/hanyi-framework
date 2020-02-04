package com.hanyi.controller;

import com.hanyi.framework.annotation.ExtController;
import com.hanyi.framework.annotation.ExtRequestMapping;
import com.hanyi.framework.annotation.ExtResource;
import com.hanyi.service.UserService;

/**
 * @ClassName: hanyi-framework com.hanyi.controller TaskController
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-02 14:50
 * @Version: 1.0
 */
@ExtController
@ExtRequestMapping("/ext")
public class TaskController {

    @ExtResource
    private UserService userService;

    @ExtRequestMapping("/test")
    public String test(String name,Integer age) {
        System.out.println("手写springmvc框架...");

        userService.order();

        return "pageIndex";
    }

}
