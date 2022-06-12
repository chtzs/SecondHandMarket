package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hackchen.secondhandmarket.annotation.AdministrationPrivilege;
import top.hackchen.secondhandmarket.beans.Review;
import top.hackchen.secondhandmarket.service.ReviewService;
import top.hackchen.secondhandmarket.util.JsonResult;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @AdministrationPrivilege
    @RequestMapping("/update")
    public JsonResult<Object> update(Integer reviewId, Integer status) {
        return reviewService.updateById(reviewId, status);
    }

    @AdministrationPrivilege
    @RequestMapping("/list")
    public JsonResult<IPage<Review>> list(@RequestParam(defaultValue = "0") Integer current,
                                          @RequestParam(defaultValue = "10") Integer size){
        return JsonResult.success(reviewService.page(new Page<>(current, size)));
    }
}
