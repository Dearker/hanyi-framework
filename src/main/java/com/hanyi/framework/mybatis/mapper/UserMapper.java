package com.hanyi.framework.mybatis.mapper;

import com.hanyi.framework.annotation.ExtInsert;
import com.hanyi.framework.annotation.ExtMapper;
import com.hanyi.framework.annotation.ExtParam;
import com.hanyi.framework.annotation.ExtSelect;
import com.hanyi.pojo.User;

/**
 * @ClassName: hanyi-framework com.hanyi.framework.mybatis.mapper UserMapper
 * @Author: weiwenchang
 * @Description: java类作用描述
 * @CreateDate: 2020-02-02 22:37
 * @Version: 1.0
 */
@ExtMapper
public interface UserMapper {

    @ExtInsert("insert into user(userName,userAge) values(#{userName},#{userAge})")
    int insertUser(@ExtParam("userName") String userName, @ExtParam("userAge") Integer userAge);

    @ExtSelect("select * from User where userName=#{userName} and userAge=#{userAge} ")
    User selectUser(@ExtParam("userName") String name, @ExtParam("userAge") Integer userAge);

}
