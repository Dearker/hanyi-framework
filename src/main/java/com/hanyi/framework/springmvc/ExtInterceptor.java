package com.hanyi.framework.springmvc;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.springmvc ExtInterceptor
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-04 11:12
 * @Version: 1.0
 */
public class ExtInterceptor implements HandlerInterceptor {

    private static final String GET = "GET";

    private static final String POST = "POST";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String method = request.getMethod();
        if(StrUtil.isNotBlank(method)){

            ExtDispatcherServlet extDispatcherServlet = new ExtDispatcherServlet();
            if(GET.equalsIgnoreCase(method)){
                extDispatcherServlet.doGet(request,response);
            }

            if(POST.equalsIgnoreCase(method)){
                extDispatcherServlet.doPost(request,response);
            }

        }

        return false;
    }
}
