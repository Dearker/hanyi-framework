package com.hanyi.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.config WebConfig
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-03 17:08
 * @Version: 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.hanyi.controller" })
public class WebConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

}
