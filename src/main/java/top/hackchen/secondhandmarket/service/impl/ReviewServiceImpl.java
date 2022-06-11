package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hackchen.secondhandmarket.beans.Review;
import top.hackchen.secondhandmarket.service.ReviewService;
import top.hackchen.secondhandmarket.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

/**
* @author wsbch
* @description 针对表【reviews】的数据库操作Service实现
* @createDate 2022-06-06 14:33:29
*/
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review>
    implements ReviewService{

}




