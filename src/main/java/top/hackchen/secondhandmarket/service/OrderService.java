package top.hackchen.secondhandmarket.service;

import top.hackchen.secondhandmarket.beans.Bid;
import top.hackchen.secondhandmarket.beans.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

public interface OrderService extends IService<Order> {
    JsonResult<Object> generateOrder(Integer bidId);
}
