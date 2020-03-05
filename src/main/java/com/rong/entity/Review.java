package com.rong.entity;

/**
 * @Description: 申请材料
 * @Author: QR
 * @Date: 2020-01-08 10:56
 **/
public class Review {
    private String id;
    private String userId;
    /**申请成为作者的材料保存路径**/
    private String freePath;
    /**申请转为付费章节的路径**/
    private String payPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFreePath() {
        return freePath;
    }

    public void setFreePath(String freePath) {
        this.freePath = freePath;
    }

    public String getPayPath() {
        return payPath;
    }

    public void setPayPath(String payPath) {
        this.payPath = payPath;
    }
}
