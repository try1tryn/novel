package com.rong.service;

import com.rong.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: QR
 * @create: 2020-01-01 16:29
 **/

public interface UserService {
    /**
     * 保存普通用户信息
     * @param user
     */
    void savePublicUser(User user);

    /**
     * 查询用户是否存在
     * @param user
     * @return
     */
    User findUser(User user);

    /**
     * 查询用户名是否存在
     * @param user
     * @return
     */
    User findUserName(User user);
}
