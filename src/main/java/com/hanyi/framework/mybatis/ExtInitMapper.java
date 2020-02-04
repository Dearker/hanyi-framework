package com.hanyi.framework.mybatis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.hanyi.framework.annotation.ExtMapper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.mybatis ExtInitMapper
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-03 09:56
 * @Version: 1.0
 */
public class ExtInitMapper {

    private String[] packageNames;

    public ExtInitMapper(String... packageNames) {
        this.packageNames = packageNames;
    }

    public Map<Set<String>,Object> initMapper() {

        if (ArrayUtil.isNotEmpty(packageNames)) {
            Map<Set<String>, Object> mapperMap = new ConcurrentHashMap<>(1 << 1);
            Set<String> mapperIdSet;
            for (String packageName : packageNames) {
                Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(packageName, ExtMapper.class);
                if (CollUtil.isNotEmpty(classes)) {
                    for (Class<?> classInfo : classes) {
                        mapperIdSet = new HashSet<>(1 << 1);

                        String lowerFirst = StrUtil.lowerFirst(classInfo.getSimpleName());
                        mapperIdSet.add(lowerFirst);

                        Object object = SqlSession.getMapper(classInfo);
                        mapperMap.put(mapperIdSet,object);
                    }
                }
            }
            return mapperMap;
        }
        return null;
    }

}
