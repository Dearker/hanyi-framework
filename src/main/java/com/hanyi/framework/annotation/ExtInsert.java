package com.hanyi.framework.annotation;

import java.lang.annotation.*;

/**
 * 自定义插入注解
 * @author weiwen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtInsert {
	String value();
}
