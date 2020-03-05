package com.rong.service.impl;

import com.rong.common.Constants;
import com.rong.entity.User;
import com.rong.mapper.UserMapper;
import com.rong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @description:
 * @author: QR
 * @create: 2020-01-01 16:32
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 保存普通用户信息
     * @param user
     */
    @Override
    public void savePublicUser(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setAuthor(Constants.USER_AUTHOR_N);
        user.setMember(Constants.USER_MEMBER_N);
        userMapper.savePublicUser(user);
    }

    /**
     * 查询用户是否存在
     * @param user
     * @return
     */
    @Override
    public User findUser(User user) {
        return userMapper.findUser(user);
    }

    /**
     * 查询用户名是否存在
     * @param user
     * @return
     */
    @Override
    public User findUserName(User user) {
        return userMapper.findUserName(user);
    }
}
