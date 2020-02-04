package com.hanyi.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.annotation ExtMapper
 * @Author: weiwenchang
 * @Description: 自定义mapper注解
 * @CreateDate: 2020-02-03 09:59
 * @Version: 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtMapper {

}
