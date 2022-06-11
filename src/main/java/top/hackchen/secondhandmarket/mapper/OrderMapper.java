package top.hackchen.secondhandmarket.mapper;

import org.springframework.stereotype.Repository;
import top.hackchen.secondhandmarket.beans.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wsbch
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2022-06-06 14:33:29
* @Entity top.hackchen.secondhandmarket.beans.Order
*/
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}




