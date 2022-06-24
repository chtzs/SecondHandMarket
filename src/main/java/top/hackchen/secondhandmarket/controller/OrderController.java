package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.beans.Bid;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.beans.Order;
import top.hackchen.secondhandmarket.service.BidService;
import top.hackchen.secondhandmarket.service.OrderService;
import top.hackchen.secondhandmarket.util.JsonResult;


@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @LoginVerify
    @RequestMapping("/gen")
    public JsonResult<Object> generateOrder(Integer bidId) {
        return orderService.generateOrder(bidId);
    }

    @LoginVerify
    @RequestMapping("/list")
    public JsonResult<Object> list(@RequestAttribute Integer userId,
                                   @RequestParam(defaultValue = "0") Integer current,
                                   @RequestParam(defaultValue = "10") Integer size) {
        IPage<Order> page = new Page<>(current, size);
        return JsonResult.success(orderService.page(page,
                new QueryWrapper<Order>().eq("buyer_id", userId)));
    }
}
