package com.rong.mapper;

import com.rong.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: QR
 * @Date: 2020-01-08 11:01
 **/
@Mapper
@Repository
public interface ReviewMapper {
    void insert(Review review);
}
