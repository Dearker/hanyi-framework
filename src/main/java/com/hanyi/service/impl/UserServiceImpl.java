package com.hanyi.service.impl;

import com.hanyi.framework.annotation.ExtResource;
import com.hanyi.framework.annotation.ExtService;
import com.hanyi.framework.mybatis.mapper.UserMapper;
import com.hanyi.pojo.User;
import com.hanyi.service.TaskService;
import com.hanyi.service.UserService;

/**
 * @ClassName: hanyi-framework com.hanyi.service.impl UserServiceImpl
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-02 12:12
 * @Version: 1.0
 */
@ExtService
public class UserServiceImpl implements UserService {

    @ExtResource
    private TaskService taskService;

    @ExtResource
    private UserMapper userMapper;

    @Override
    public void order() {
        User selectUser = userMapper.selectUser("hanyi", 12);
        taskService.task();
        System.out.println("userService");
        System.out.println(
                "结果:" + selectUser.getUserName() + "," + selectUser.getUserAge() + ",id:" + selectUser.getId());
    }

}
