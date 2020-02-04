package com.hanyi.service.impl;

import com.hanyi.framework.annotation.ExtService;
import com.hanyi.service.TaskService;

/**
 * @ClassName: hanyi-framework com.hanyi.service.impl TaskServiceImpl
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-02 12:10
 * @Version: 1.0
 */
@ExtService
public class TaskServiceImpl implements TaskService {

    @Override
    public void task() {
        System.out.println("taksService");
    }

}
