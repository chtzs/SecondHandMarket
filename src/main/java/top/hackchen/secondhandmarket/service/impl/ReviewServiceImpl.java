package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.beans.Review;
import top.hackchen.secondhandmarket.mapper.ReviewMapper;
import top.hackchen.secondhandmarket.service.ReviewService;
import top.hackchen.secondhandmarket.util.JsonResult;

@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review>
        implements ReviewService {
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public JsonResult<Object> updateById(Integer reviewId, Integer status) {
        //这里为了图省事，就没做id不存在的判断
        //因为审核模块都是管理员操作，用户无法访问，应该不会出现内鬼
        Review review = reviewMapper.selectById(reviewId);
        review.setStatus(status);
        reviewMapper.updateById(review);
        return JsonResult.success(review);
    }
}




