package com.hanyi.framework.spring;

import java.lang.reflect.Field;
import java.util.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.hanyi.framework.annotation.ExtResource;
import com.hanyi.framework.annotation.ExtService;
import com.hanyi.framework.mybatis.ExtInitMapper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.spring ExtClassPathXmlApplication
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-01 23:13
 * @Version: 1.0
 */
public class ExtClassPathXmlApplication {

    private static final Log logger = LogFactory.get(ExtClassPathXmlApplication.class);

    private String[] packageNames;

    public Map<Set<String>, Object> springIocMap;

    public ExtClassPathXmlApplication(String... packageName) {
        this.packageNames = packageName;
        initSpringIoc();
    }

    public Object getBean(String beanId) {
        return getBean(beanId, springIocMap);
    }

    private Object getBean(String beanId, Map<Set<String>, Object> iocMap) {

        if (StrUtil.isBlank(beanId)) {
            throw new IllegalArgumentException("beanId cannot empty");
        }

        Object object = null;
        for (Map.Entry<Set<String>, Object> entry : iocMap.entrySet()) {
            Set<String> k = entry.getKey();
            if (k.contains(beanId)) {
                object = entry.getValue();
            }
        }
        return object;
    }

    private void initSpringIoc() {

        if (ArrayUtil.isNotEmpty(packageNames)) {

            springIocMap = new ConcurrentHashMap<>(4 << 1);
            for (String packageName : packageNames) {

                Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(packageName, ExtService.class);
                if (CollUtil.isNotEmpty(classes)) {
                    Set<String> stringSet;
                    for (Class<?> classInfo : classes) {
                        stringSet = new HashSet<>(2 << 1);

                        setSpringIocMapKey(classInfo, stringSet);

                        Object object = ReflectUtil.newInstance(classInfo);
                        springIocMap.put(stringSet, object);
                    }

                }
            }
            setParentMap();
            initAttriAssign(springIocMap);
            logger.info("Spring IOC init Finished");
        }
    }


    public void initAttriAssign(Map<Set<String>,Object> iocMap) {

        Collection<Object> values = iocMap.values();

        if(CollUtil.isNotEmpty(values)){
            values.forEach(object ->{
                Class<?> classInfo = object.getClass();
                Field[] declaredFields = classInfo.getDeclaredFields();
                for (Field field : declaredFields) {
                    // 属性名称
                    ExtResource extResource = field.getDeclaredAnnotation(ExtResource.class);
                    if (Objects.nonNull(extResource)) {
                        String name = field.getName();
                        // 2.使用属性名称查找bean容器赋值
                        Object bean = getBean(name,iocMap);
                        if (Objects.nonNull(bean)) {
                            // 私有访问允许访问
                            field.setAccessible(true);
                            // 给属性赋值
                            ReflectUtil.setFieldValue(object,field,bean);
                        }
                    }
                }
            });
            logger.info("Spring Attribute Assign Finished");
        }
    }

    private void setSpringIocMapKey(Class<?> classInfo, Set<String> stringSet) {
        String simpleName = classInfo.getSimpleName();
        stringSet.add(simpleName);

        String lowerFirst = StrUtil.lowerFirst(simpleName);
        stringSet.add(lowerFirst);

        Class<?>[] classInfoInterfaces = classInfo.getInterfaces();

        for (Class<?> classInfoInterface : classInfoInterfaces) {
            String simpleNameInterface = classInfoInterface.getSimpleName();
            stringSet.add(simpleNameInterface);
            String lowerFirstInterface = StrUtil.lowerFirst(simpleNameInterface);
            stringSet.add(lowerFirstInterface);
        }
    }

    private void setParentMap() {

        ExtInitMapper extInitMapper = new ExtInitMapper(packageNames);
        Map<Set<String>, Object> initMapperMap = extInitMapper.initMapper();

        if (CollUtil.isNotEmpty(initMapperMap)) {
            logger.info("Spring ParentMap init Finished");
            springIocMap.putAll(initMapperMap);
        }
    }

}
