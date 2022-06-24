package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.hackchen.secondhandmarket.beans.Bid;
import top.hackchen.secondhandmarket.beans.Order;
import top.hackchen.secondhandmarket.mapper.BidMapper;
import top.hackchen.secondhandmarket.service.BidService;
import top.hackchen.secondhandmarket.service.OrderService;
import top.hackchen.secondhandmarket.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.util.JsonResult;
import top.hackchen.secondhandmarket.util.RandomUtils;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public JsonResult<Object> generateOrder(Integer bidId) {
        Bid bid = bidMapper.selectById(bidId);
        Order order = new Order();
        order.setOrderNumber(RandomUtils.createRandomUUID() + RandomUtils.createRandomUUID());
        order.setActualPay(bid.getOffer());
        order.setBuyerId(bid.getBuyerId());
        order.setGoodsId(bid.getGoodsId());
        order.setTradeStatus(0);
        order.setDeliveryStatus(0);
        orderMapper.insert(order);
        return JsonResult.success("生成成功");
    }
}




