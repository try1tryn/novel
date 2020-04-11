package com.rong.service;

import com.rong.entity.Book;
import com.rong.entity.Chapter;

import java.util.List;

/**
 * @description:
 * @author: QR
 * @create: 2019-12-29 17:11
 **/
public interface BookService {
    /**
     * 根据数据和章节名 获取小说章节的存储位置
     * @param chapter
     * @return
     */
    Chapter findChapterPath(Chapter chapter);

    /**
     * 保存书 信息
     * @param book
     */
    void saveBook(Book book);

    /**
     * 保存书的章节信息
     * @param chapter
     */
    void saveChapter(Chapter chapter);
    /**
     * 获取小说的阅读量
     * @param book
     * @return
     */
    Book findReadCount(Book book);

    /**
     * 更新小说的阅读量
     * @param book
     */
    void updateReadCount(Book book);
    /**
     * 查看小说详情
     * @param book
     * @return
     */
    Book findBookDetail(Book book);
    /**
     * 删除小说
     * @param book
     */
    void deleteBook(Book book);

}
