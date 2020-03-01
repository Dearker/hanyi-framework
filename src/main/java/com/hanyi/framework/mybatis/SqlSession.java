package com.hanyi.framework.mybatis;


import java.lang.reflect.Proxy;

/**
 * @author weiwen
 */
class SqlSession {

	/**
	 * 加载Mapper接口
	 * @param classz
	 * @param <T>
	 * @return
	 */
	static <T> T getMapper(Class<T> classz) {
		return (T) Proxy.newProxyInstance(classz.getClassLoader(),
				                          new Class[] { classz },
				                          new ExtInvocationHandlerMbatis(classz));
	}

}
