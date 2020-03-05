package com.rong.mapper;

import com.rong.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author: QR
 * @create: 2019-12-29 18:02
 **/
@Repository
@Mapper
public interface ChapterMapper {
    /**
     * 保存章节信息
     * @param chapter
     */
    void saveChapter(Chapter chapter);

    /**
     * 通过小说名和章节名 获取存放的路径
     * @param chapter
     * @return
     */
    Chapter findChapterPath(Chapter chapter);
}
