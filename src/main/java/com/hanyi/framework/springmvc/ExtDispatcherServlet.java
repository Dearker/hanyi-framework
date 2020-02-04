package com.hanyi.framework.springmvc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.hanyi.framework.annotation.ExtController;
import com.hanyi.framework.annotation.ExtRequestMapping;
import com.hanyi.framework.spring.ExtClassPathXmlApplication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.springmvc ExtDispatcherServlet
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-02 19:13
 * @Version: 1.0
 */
public class ExtDispatcherServlet extends HttpServlet {

    /**
     * springmvc 容器对象 key:类名id ,value 对象
     */
    private Map<Set<String>, Object> springmvcBeans;
    /**
     * springmvc 容器对象 keya:请求地址 ,vlue类
     */
    private Map<String, Object> urlBeans;
    /**
     * springmvc 容器对象 key:请求地址 ,value 方法名称
     */
    private Map<String, String> urlMethods;

    private String[] packageNames;


    public ExtDispatcherServlet(String... packageNames) {
        this.packageNames = packageNames;
    }

    @Override
    public void init() {

        if(ArrayUtil.isNotEmpty(packageNames)){
            springmvcBeans = new ConcurrentHashMap<>(4 << 1);
            Set<String> beanIdSet;
            for (String packageName : packageNames) {
                Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(packageName, ExtController.class);
                if(CollUtil.isNotEmpty(classes)){
                    for (Class<?> classInfo : classes) {
                        ExtController extController = classInfo.getDeclaredAnnotation(ExtController.class);
                        if (Objects.nonNull(extController)) {
                            beanIdSet = new HashSet<>(1 << 1);
                            // 默认类名是小写
                            String beanId = StrUtil.lowerFirst(classInfo.getSimpleName());
                            beanIdSet.add(beanId);
                            // 实例化对象
                            try {
                                Object object = classInfo.newInstance();
                                springmvcBeans.put(beanIdSet, object);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            setParentMap();
            handlerMapping();
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * 请求处理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取请求url地址
        String requestURI = req.getRequestURI();
        if (StrUtil.isEmpty(requestURI)) {
            return;
        }
        // 2.从Map集合中获取控制对象
        Object object = urlBeans.get(requestURI);
        if (Objects.isNull(object)) {
            resp.getWriter().println(" not found 404  url");
            return;
        }
        // 3.使用url地址获取方法
        String methodName = urlMethods.get(requestURI);
        if (StrUtil.isEmpty(methodName)) {
            resp.getWriter().println(" not found method");
        }
        // 4.使用java的反射机制调用方法
        String resultPage = (String) methodInvoke(object, methodName);
        // 5.调用视图转换器渲染给页面展示
        extResourceViewResolver(resultPage, req, resp);
    }

    /**
     * 将url映射和方法进行关联
     */
    private void handlerMapping() {

        if(CollUtil.isNotEmpty(springmvcBeans)){
            urlBeans = new ConcurrentHashMap<>(4 << 1);
            urlMethods = new ConcurrentHashMap<>(4 << 1);

            // 1.遍历springmvc bean容器 判断类上属否有url映射注解
            springmvcBeans.values().forEach(object ->{
                // 2.遍历所有的方法上是否有url映射注解
                // 3.判断类上是否有加url映射注解
                Class<?> classInfo = object.getClass();
                ExtRequestMapping declaredAnnotation = classInfo.getDeclaredAnnotation(ExtRequestMapping.class);
                String baseUrl = "";
                if (Objects.nonNull(declaredAnnotation)) {
                    // 获取类上的url映射地址
                    baseUrl = declaredAnnotation.value();
                }
                // 4.判断方法上是否有加url映射地址
                Method[] declaredMethods = classInfo.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    // 判断方法上是否有加url映射注解
                    ExtRequestMapping methodExtRequestMapping = method.getDeclaredAnnotation(ExtRequestMapping.class);
                    if (Objects.nonNull(methodExtRequestMapping)) {
                        String methodUrl = baseUrl + methodExtRequestMapping.value();
                        // springmvc 容器对象 keya:请求地址 ,vlue类
                        urlBeans.put(methodUrl, object);
                        // springmvc 容器对象 key:请求地址 ,value 方法名称
                        urlMethods.put(methodUrl, method.getName());
                    }
                }

            });
        }
    }


    private void setParentMap(){

        ExtClassPathXmlApplication extClassPathXmlApplication = new ExtClassPathXmlApplication(packageNames);
        extClassPathXmlApplication.initAttriAssign(springmvcBeans);
        Map<Set<String>, Object> initSpringIoc = extClassPathXmlApplication.springIocMap;
        if(CollUtil.isNotEmpty(initSpringIoc)){
            springmvcBeans.putAll(initSpringIoc);
        }
    }


    private void extResourceViewResolver(String pageName, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 根路径
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix + pageName + suffix).forward(req, res);
    }

    private Object methodInvoke(Object object, String methodName) {
        try {
            Class<?> classInfo = object.getClass();
            Method method = classInfo.getMethod(methodName);
            return method.invoke(object);
        } catch (Exception e) {
            return null;
        }

    }

}
