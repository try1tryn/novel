package com.rong.entity;

/**
 * @Title:
 * @Description: 功能概述: 小说书籍信息
 * @Author: ZQR
 * @version: 1.0 .
 * @Date: 2019-12-23 14:15
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class Book {
    private String id;
    private String bookName;
    private String bookPath;
    private String author;
    private String bookStatus;
    private String bookImg;
    private String introduction;
    /**阅读量**/
    private Integer readCount;

    public Book() {
    }

    public Book(String id, String bookName, String bookPath, String author, String bookStatus, String bookImg, String introduction, Integer readCount) {
        this.id = id;
        this.bookName = bookName;
        this.bookPath = bookPath;
        this.author = author;
        this.bookStatus = bookStatus;
        this.bookImg = bookImg;
        this.introduction = introduction;
        this.readCount = readCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookPath='" + bookPath + '\'' +
                ", author='" + author + '\'' +
                ", bookStatus='" + bookStatus + '\'' +
                ", bookImg='" + bookImg + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
