package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.hackchen.secondhandmarket.beans.Bid;
import top.hackchen.secondhandmarket.enums.SortOrder;
import top.hackchen.secondhandmarket.mapper.UserMapper;
import top.hackchen.secondhandmarket.service.BidService;
import top.hackchen.secondhandmarket.mapper.BidMapper;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.service.GoodsService;
import top.hackchen.secondhandmarket.service.UserService;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid>
        implements BidService {
    private final UserService userService;
    private final GoodsService goodsService;
    private final BidMapper bidMapper;

    public BidServiceImpl(UserService userService, GoodsService goodsService, BidMapper bidMapper) {
        this.userService = userService;
        this.goodsService = goodsService;
        this.bidMapper = bidMapper;
    }

    @Override
    public JsonResult<Object> offer(Integer buyerId, Integer goodsId, BigDecimal price) {
        if (!userService.isExist(buyerId)) return JsonResult.USER_NOT_EXIST;
        if (!goodsService.goodsExist(goodsId)) return JsonResult.GOODS_NOT_EXIST;
        Bid bid = new Bid();
        bid.setOffer(price);
        bid.setBuyerId(buyerId);
        bid.setGoodsId(goodsId);
        bid.setCreateDate(new Date());
        bidMapper.insert(bid);
        return JsonResult.success("投标成功", bid);
    }

    @Override
    public JsonResult<IPage<Bid>> listPageByOffer(IPage<Bid> page, Integer goodsId, SortOrder order) {
        QueryWrapper<Bid> query = new QueryWrapper<Bid>()
                .eq("goods_id", goodsId);
        if (order == SortOrder.ASC) query.orderByAsc("offer");
        else query.orderByDesc("offer");

        IPage<Bid> bids = bidMapper.selectPage(page, query);
        return JsonResult.success("查询成功", bids);
    }

    @Override
    public JsonResult<IPage<Bid>> listPageByDate(IPage<Bid> page, Integer goodsId, SortOrder order) {
        QueryWrapper<Bid> query = new QueryWrapper<Bid>()
                .eq("goods_id", goodsId);
        if (order == SortOrder.ASC) query.orderByAsc("create_date");
        else query.orderByDesc("create_date");

        IPage<Bid> bids = bidMapper.selectPage(page, query);
        return JsonResult.success("查询成功", bids);
    }
}




