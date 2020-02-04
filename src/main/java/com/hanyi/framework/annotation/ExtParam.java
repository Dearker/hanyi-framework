package com.hanyi.framework.annotation;

import java.lang.annotation.*;

/**
 * 自定义参数注解
 * @author weiwen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ExtParam {
	String value();
}
