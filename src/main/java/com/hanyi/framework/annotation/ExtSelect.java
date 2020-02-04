package com.hanyi.framework.annotation;

import java.lang.annotation.*;

/**
 * 自定义查询注解
 * @author weiwen
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtSelect {

	String value();

}
