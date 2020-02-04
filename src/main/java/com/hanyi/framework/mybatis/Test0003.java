package com.hanyi.framework.mybatis;

import com.hanyi.framework.mybatis.mapper.UserMapper;
import com.hanyi.pojo.User;

public class Test0003 {

	public static void main(String[] args) {
		// 使用动态代理技术虚拟调用方法
		UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
		User selectUser = userMapper.selectUser("hanyi", 12);
		System.out.println(
				"结果:" + selectUser.getUserName() + "," + selectUser.getUserAge() + ",id:" + selectUser.getId());
		// // 先走拦截invoke
		// int insertUserResult = userMapper.insertUser("张三", 644064);
		// System.out.println("insertUserResult:" + insertUserResult);


	}

}
