package com.rong.mapper;

import com.rong.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: QR
 * @create: 2020-01-01 20:57
 **/
@Repository
@Mapper
public interface CollectMapper {
    /**
     * 用户收藏书信息
     * @param collect
     */
    void saveCollect(Collect collect);

    /**
     * 用户取消收藏
     * @param collect
     */
    void deleteCollect(Collect collect);

    /**
     * 查询该书籍是否被收藏
     * @param collect
     * @return
     */
    Collect findCollectResult(Collect collect);

    /**
     * 修改书籍收藏状态
     * @param collect
     */
    void updateCollectStatus(Collect collect);
}
