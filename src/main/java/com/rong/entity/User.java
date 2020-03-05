package com.rong.entity;

import java.io.Serializable;

/**
 * @description: 用户信息
 * @author: QR
 * @create: 2020-01-01 16:19
 **/
public class User implements Serializable {
    private String id;
    private String userName;
    private String password;
    /**1 作者 0 非作者 **/
    private String author;
    /**1 会员 0 非会员 **/
    private String member;
    private String phoneNumber;

    /** 冗余 验证码 **/
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", author='" + author + '\'' +
                ", member='" + member + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
