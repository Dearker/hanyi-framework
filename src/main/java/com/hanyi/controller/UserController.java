package com.hanyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: hanyi-framework com.hanyi.controller UserController
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-03 19:04
 * @Version: 1.0
 */
@Controller
public class UserController {

    @RequestMapping("/pageIndex")
    public String pageIndex() {
        return "pageIndex";
    }

}
