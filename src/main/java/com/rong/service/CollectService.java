package com.rong.service;

import com.rong.entity.Collect;

/**
 * @description:
 * @author: QR
 * @create: 2020-01-01 21:31
 **/
public interface CollectService {
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
