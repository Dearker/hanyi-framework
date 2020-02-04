package com.hanyi.framework.springmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.springmvc WebMvcConfig
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-04 11:34
 * @Version: 1.0
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截使用请求
        registry.addInterceptor(new ExtInterceptor()).addPathPatterns("/**");
    }

}
