package com.hanyi.framework.springmvc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.springmvc ExtAspect
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-04 11:48
 * @Version: 1.0
 */
@Aspect
@Component
public class ExtAspect {

    //@Around("@annotation(com.hanyi.framework.annotation.ExtController)")
    @Around("execution(* com.hanyi.controller.*.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable {
        System.out.println("time aspect start");
        long start = System.currentTimeMillis();
        Object[] args = point.getArgs();
        for (Object obj : args) {
            System.out.println("arg is:"+obj);
        }
        //具体方法的返回值
        Object obj = point.proceed();
        System.out.println("aspect 耗时："+(System.currentTimeMillis()-start));
        System.out.println("time aspect end");
        return obj;
    }


}
