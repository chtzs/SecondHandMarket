package top.hackchen.secondhandmarket.service;

import top.hackchen.secondhandmarket.beans.Review;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

public interface ReviewService extends IService<Review> {
    JsonResult<Object> updateById(Integer reviewId, Integer status);
}
