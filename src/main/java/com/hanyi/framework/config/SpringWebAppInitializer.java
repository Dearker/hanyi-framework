package com.hanyi.framework.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.config SpringWebAppInitializer
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-03 14:40
 * @Version: 1.0
 */
public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * spring容器
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * springmvc容器
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class[] {WebConfig.class};
    }

    /**
     * 拦截所有请求
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
