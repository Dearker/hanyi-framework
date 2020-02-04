package com.hanyi.framework.annotation;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.annotation ExtSpringBootApplication
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-01 22:19
 * @Version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface ExtSpringBootApplication {

    String[] scanBasePackages() default {};

}
