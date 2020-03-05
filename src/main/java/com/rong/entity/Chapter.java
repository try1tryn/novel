package com.rong.entity;

/**
 * @description: 小说章节
 * @author: QR
 * @create: 2019-12-29 18:00
 **/
public class Chapter {
    private String id;
    private String bookId;
    private String chapter;
    private String chapterPath;
    private String bookName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getChapterPath() {
        return chapterPath;
    }

    public void setChapterPath(String chapterPath) {
        this.chapterPath = chapterPath;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", bookId='" + bookId + '\'' +
                ", chapter='" + chapter + '\'' +
                ", chapterPath='" + chapterPath + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
