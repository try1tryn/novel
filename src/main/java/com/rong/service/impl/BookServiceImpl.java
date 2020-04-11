package com.rong.service.impl;

import com.rong.entity.Book;
import com.rong.entity.Chapter;
import com.rong.mapper.BookMapper;
import com.rong.mapper.ChapterMapper;
import com.rong.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: QR
 * @create: 2019-12-29 17:12
 **/
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 根据数据和章节名 获取小说章节的存储位置
     * @param chapter
     * @return
     */
    @Override
    public Chapter findChapterPath(Chapter chapter) {
        return chapterMapper.findChapterPath(chapter);
    }

    /**
     * 保存书 信息
     * @param book
     */
    @Override
    public void saveBook(Book book) {
        System.out.println(book);
        bookMapper.saveBook(book);
    }
    /**
     * 保存书的章节信息
     * @param chapter
     */
    @Override
    public void saveChapter(Chapter chapter) {
        chapterMapper.saveChapter(chapter);
    }
    /**
     * 获取小说的阅读量
     * @param book
     * @return
     */
    @Override
    public Book findReadCount(Book book) {
        return bookMapper.findReadCount(book);
    }
    /**
     * 更新小说的阅读量
     * @param book
     */
    @Override
    public void updateReadCount(Book book) {
         bookMapper.updateReadCount(book);
    }
    /**
     * 查看小说详情
     * @param book
     * @return
     */
    @Override
    public Book findBookDetail(Book book) {
        return bookMapper.findBookDetail(book);
    }

    /**
     * 删除小说
     * @param book
     */
    @Override
    public void deleteBook(Book book) {
        bookMapper.deleteBook(book);
    }
}
