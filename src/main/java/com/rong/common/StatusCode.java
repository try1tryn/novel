package com.rong.common;

/**
 * @description: 状态码
 * @author: QR
 * @create: 2020-01-05 14:54
 **/
public class StatusCode {
    //成功
    public static final int OK = 20000;
    //失败
    public static final int ERROR = 20001;
    //用户名或密码错误
    public static final int LOGINERROR = 20002;
    //权限不足
    public static final int ACCESSERROR = 20003;
    //远程调用失败
    public static final int REMOTEERROR = 20004;
    //重复操作
    public static final int REPERROR = 20005;
    //验证失败
    public static final int JUDGEERROR =20006;
    //用户名重复
    public static final int USERERROR = 20007;
}
