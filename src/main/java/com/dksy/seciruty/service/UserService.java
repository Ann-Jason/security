package com.dksy.seciruty.service;

import com.dksy.seciruty.dao.UserDao;
import com.dksy.seciruty.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> queryUserList(){
        return userDao.queryAllUser();
    }


}
