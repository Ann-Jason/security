package com.dksy.seciruty.dao;

import com.dksy.seciruty.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> queryAllUser();
    @Select("select * from user where username = #{username}")
    User queryUserByUsername(@Param("username")String username);

}
