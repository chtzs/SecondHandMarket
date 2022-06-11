package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hackchen.secondhandmarket.beans.Order;
import top.hackchen.secondhandmarket.service.OrderService;
import top.hackchen.secondhandmarket.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author wsbch
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2022-06-06 14:33:29
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




