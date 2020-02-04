package com.hanyi.framework.springboot;

import com.hanyi.framework.spring.ExtClassPathXmlApplication;
import com.hanyi.service.UserService;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.springboot SpringbootTest
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-01 21:12
 * @Version: 1.0
 */
public class SpringbootTest {

    public static void main(String[] args) {

        ExtClassPathXmlApplication extClassPathXmlApplication = new ExtClassPathXmlApplication("com.hanyi");
        UserService userService = (UserService) extClassPathXmlApplication.getBean("UserService");
        userService.order();

    }

}
