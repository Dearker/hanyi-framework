package com.hanyi;

import com.hanyi.framework.annotation.ExtSpringBootApplication;
import com.hanyi.framework.springboot.ExtSpringApplication;

/**
 * @ClassName: hanyi-framework com.hanyi HanyiApplication
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-01 22:16
 * @Version: 1.0
 */
@ExtSpringBootApplication
public class HanyiApplication {

    public static void main(String[] args) {
        ExtSpringApplication.run(HanyiApplication.class,args);
    }

}
