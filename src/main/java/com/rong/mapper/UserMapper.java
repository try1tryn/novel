package com.rong.mapper;

import com.rong.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: QR
 * @create: 2020-01-01 16:21
 **/
@Repository
@Mapper
public interface UserMapper {
    /**
     * 保存普通用户信息
     * @param user
     */
    void savePublicUser(User user);

    /**
     * 校验该用户和密码是否匹配
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
