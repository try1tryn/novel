package com.rong.controller;


import com.rong.annotation.PassToken;
import com.rong.common.*;
import com.rong.entity.*;
import com.rong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 用户模块
 * @author: QR
 * @create: 2020-01-01 16:44
 **/

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    /**
     * 普通用户注册校验
     *
     * @param user
     * @return
     */
    @PassToken
    @RequestMapping("/registerCondition")
    public Result registerJudge(@RequestBody User user) {
        // 在用户表中查询是否存在该用户名
        User userMsg = userService.findUserName(user);
        // 用户名唯一
        if (userMsg == null) {
            // 校验手机号码的合法性
            if (PhoneNumJudge.isMobile(user.getPhoneNumber())) {
                // Math.random()*9 表示随机的范围 0-9 不包括9
                // Math.random()*9+1 范围 0-9 再乘以1000 就是0-9999的四字随机数
                // 将验证码和用户信息放入缓存
                redisTemplate.boundValueOps(Constants.CODE).set(String.valueOf((int) ((Math.random() * 9 + 1) * 1000)));
                redisTemplate.boundValueOps(Constants.USER_REGISTER_MSG).set(user);
                System.out.println("验证码----" + redisTemplate.boundValueOps(Constants.CODE).get());
                return new Result(true, StatusCode.OK, "请输入验证码");
            } else {
                return new Result(false, StatusCode.JUDGEERROR, "手机号码不合法 请重新输入");
            }
        }
        return new Result(false, StatusCode.USERERROR, "该用户已经存在,请重新输入用户名");
    }

    /**
     * 普通用户注册
     *
     * @return
     */
    @PassToken
    @RequestMapping("/register")
    public Result register(@RequestBody User user) {
        if (user.getCode().equals(redisTemplate.boundValueOps(Constants.CODE).get())) {
            // 验证码输入成功
            User userMsg = (User) redisTemplate.boundValueOps(Constants.USER_REGISTER_MSG).get();
            userService.savePublicUser(userMsg);
            return new Result(true, StatusCode.OK, "注册成功");
        }
        return new Result(false, StatusCode.JUDGEERROR, "校验码错误,请重新输入");
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PassToken
    @RequestMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        if (userService.findUser(user) == null) {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
        // 用户校验通过，生成令牌--->保存令牌到客户端
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.USER_REGISTER_MSG, user);
        String info = JSON.toJSONString(map);
        String token = JwtUtils.createJwt(UUID.randomUUID().toString(), info, null);

        Cookie cookie = new Cookie(Constants.AUTHORIZATION, token);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        // 在缓存里面存放token和user信息
        redisTemplate.boundValueOps(Constants.AUTHORIZATION).set(token);
        redisTemplate.boundValueOps(token).set(user);


        return new Result(true, StatusCode.OK, "登陆成功");
    }

    /**
     * 用户申请成为作者 需要上传一篇文章 进行审核
     */



}
