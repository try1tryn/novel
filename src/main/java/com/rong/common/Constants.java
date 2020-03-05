package com.rong.common;

/**
 * @Description:常量类
 * @author: QR
 * @create: 2020-01-02 23:13
 **/
public class Constants {
    /** 书籍收藏状态 1 收藏 0 取消收藏**/
    public final static String COLLECT_STATUS_Y = "1";
    public final static String COLLECT_STATUS_N = "0";
    /**1 作者 0 非作者 **/
    //public final static String USER_AUTHOR_Y = "1";
    public final static String USER_AUTHOR_N = "0";
    /**1 会员 0 非会员 **/
   // public final static String USER_MEMBER_Y = "1";
    public final static String USER_MEMBER_N = "0";
    /** 随机验证码key**/
    public final static String CODE = "code";
    /** 用户注册信息**/
    public final static String USER_REGISTER_MSG = "userMsg";
    /**有效期为一个小时 **/
    public static final Long JWT_TTL = 3600000L;
    /**Jwt令牌信息**/
    public static final String JWT_KEY = "tutu";
    /**cookie 存放token的key**/
    public static final String AUTHORIZATION = "Authorization";
    /**redis 过期时间界限**/
    public static final Long REDIS_EXPIRE = 10L;
    /**Jwt签发者**/
    public static final String JWT_ROLE = "admin";
}
