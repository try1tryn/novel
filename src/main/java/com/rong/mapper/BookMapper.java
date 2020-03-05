package com.rong.mapper;

import com.rong.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @Title:
 * @Description: 功能概述:
 * @Author: ZQR
 * @version: 1.0 .
 * @Date: 2019-12-23 14:18
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
@Repository
@Mapper
public interface BookMapper {
    /**
     * 保存信息
     * @param book
     */
    void saveBook(Book book);

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
