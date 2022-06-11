package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hackchen.secondhandmarket.beans.Bid;
import top.hackchen.secondhandmarket.service.BidService;
import top.hackchen.secondhandmarket.mapper.BidMapper;
import org.springframework.stereotype.Service;

/**
* @author wsbch
* @description 针对表【bids】的数据库操作Service实现
* @createDate 2022-06-06 14:33:29
*/
@Service
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid>
    implements BidService{

}




