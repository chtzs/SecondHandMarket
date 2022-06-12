package top.hackchen.secondhandmarket.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.hackchen.secondhandmarket.beans.Bid;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.enums.SortOrder;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.math.BigDecimal;

public interface BidService extends IService<Bid> {
    JsonResult<Object> offer(Integer buyerId, Integer goodsId, BigDecimal price);

    JsonResult<IPage<Bid>> listPageByOffer(IPage<Bid> page, Integer goodsId, SortOrder order);

    JsonResult<IPage<Bid>> listPageByDate(IPage<Bid> page, Integer goodsId, SortOrder order);
}
