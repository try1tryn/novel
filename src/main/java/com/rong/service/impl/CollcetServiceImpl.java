package com.rong.service.impl;

import com.rong.entity.Collect;
import com.rong.mapper.CollectMapper;
import com.rong.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: QR
 * @create: 2020-01-01 21:32
 **/
@Service
public class CollcetServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 用户收藏书信息
     * @param collect
     */
    @Override
    public void saveCollect(Collect collect) {
             collectMapper.saveCollect(collect);
    }

    /**
     * 用户取消收藏
     * @param collect
     */
    @Override
    public void deleteCollect(Collect collect) {
           collectMapper.deleteCollect(collect);
    }

    @Override
    public Collect findCollectResult(Collect collect) {
        return collectMapper.findCollectResult(collect);
    }

    @Override
    public void updateCollectStatus(Collect collect) {
        collectMapper.updateCollectStatus(collect);
    }
}
