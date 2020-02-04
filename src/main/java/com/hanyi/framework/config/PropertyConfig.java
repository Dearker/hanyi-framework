package com.hanyi.framework.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.config PropertyConfig
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-03 20:38
 * @Version: 1.0
 */
public class PropertyConfig {

    public static PropertyConfig PROPERTYCONFIG = new PropertyConfig();

    private Set<Props> propsSet;

    private PropertyConfig() {
        initProperties();
    }

    private void initProperties() {

        Set<String> propertyNameSet = new HashSet<>(2 << 1);
        propertyNameSet.add("application.properties");

        propsSet = new HashSet<>(4 << 1);
        Props prop;
        for (String property : propertyNameSet) {
            prop = new Props(property);
            propsSet.add(prop);
        }
    }

    public String getStr(String name) {

        if (StrUtil.isNotBlank(name)) {
            for (Props props : propsSet) {
                String propsStr = props.getStr(name);
                if (StrUtil.isNotBlank(propsStr)) {
                    return propsStr;
                }
            }
        }
        return null;
    }

    public Integer getInteger(String name) {

        if (StrUtil.isNotBlank(name)) {
            for (Props props : propsSet) {
                Integer propsInt = props.getInt(name);
                if (Objects.nonNull(propsInt)) {
                    return propsInt;
                }
            }
        }
        return null;
    }

}
