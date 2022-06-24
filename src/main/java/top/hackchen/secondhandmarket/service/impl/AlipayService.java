package top.hackchen.secondhandmarket.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.beans.Order;
import top.hackchen.secondhandmarket.mapper.GoodsMapper;
import top.hackchen.secondhandmarket.mapper.OrderMapper;
import top.hackchen.secondhandmarket.service.PayService;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.util.Date;

@Service
public class AlipayService implements PayService {
    private static final String BACKEND_URL = "http://zn8qsd.natappfree.cc/";
    private static final String FRONT_URL = "http://zn8qsd.natappfree.cc/";
    private static final String NOTIFY_URL = BACKEND_URL + "api/pay/notify";
    private static final String RETURN_URL = FRONT_URL + "home";
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public String generatePayForm(Integer orderId) {
        Order order = orderMapper.selectById(orderId);
        Goods goods = goodsMapper.selectById(order.getGoodsId());
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(NOTIFY_URL);
        request.setReturnUrl(RETURN_URL);
        request.setBizContent("{\"out_trade_no\":\"" + order.getOrderNumber() + "\","
                + "\"total_amount\":\"" + order.getActualPay() + "\","
                + "\"subject\":\"" + goods.getName() + "\","
                + "\"body\":\"" + "非常好！" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try {
            return alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    @Override
    public void notify(Integer orderId, String alipayTradeNumber) {
        Order order = orderMapper.selectById(orderId);
        order.setPayDate(new Date());
        order.setTradeStatus(1);
        order.setAlipayTradeNumber(alipayTradeNumber);
        orderMapper.updateById(order);
    }

    @Override
    public void paySuccess(Integer orderId) {
        Order order = orderMapper.selectById(orderId);
        order.setPayDate(new Date());
        order.setTradeStatus(2);
        orderMapper.updateById(order);
    }
}
