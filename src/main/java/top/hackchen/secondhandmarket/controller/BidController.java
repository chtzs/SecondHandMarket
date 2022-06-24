package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.beans.Bid;
import top.hackchen.secondhandmarket.enums.SortOrder;
import top.hackchen.secondhandmarket.service.BidService;
import top.hackchen.secondhandmarket.service.GoodsService;
import top.hackchen.secondhandmarket.util.JsonResult;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/bid")
public class BidController {
    private final BidService bidService;
    private final GoodsService goodsService;

    public BidController(BidService bidService, GoodsService goodsService) {
        this.bidService = bidService;
        this.goodsService = goodsService;
    }

    @LoginVerify
    @RequestMapping("/offer")
    public JsonResult<Object> offer(@RequestAttribute Integer userId, Integer goodsId, BigDecimal price) {
        return bidService.offer(userId, goodsId, price);
    }

    @LoginVerify
    @RequestMapping("/list")
    public JsonResult<?> listPage(@RequestParam(defaultValue = "0") Integer current,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  @RequestAttribute Integer userId,
                                  Integer goodsId,
                                  String column,
                                  String order) {
        Assert.isTrue(goodsService.goodsBelongTo(userId, goodsId), "当前用户不是该商品的卖家");
        Assert.isTrue("offer".equals(column)
                || "date".equals(column), "未知的列：" + column);
        Assert.isTrue("asc".equals(order)
                || "desc".equals(order), "未知的排序方式：" + order);

        //开始查询
        Page<Bid> page = new Page<>(current, size);
        SortOrder o = SortOrder.valueOf(order.toUpperCase());
        if ("offer".equals(column))
            return bidService.listPageByOffer(page, goodsId, o);
        else
            return bidService.listPageByDate(page, goodsId, o);
    }

    @LoginVerify
    @RequestMapping("/delete")
    JsonResult<Object> delete(@RequestAttribute("userId") Integer sellerId, Integer bidId) {
        Bid bid = bidService.getById(bidId);
        Assert.notNull(bid, "id为" + bidId + "的报价不存在");
        Assert.isTrue(goodsService.goodsBelongTo(sellerId, bid.getGoodsId()), "当前用户不是该商品的卖家");
        bidService.removeById(bid);
        return JsonResult.success("删除成功");
    }

    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    JsonResult<Object> handleConstraintViolationException(Exception e) {
        return JsonResult.failed(JsonResult.BAD_REQUEST, e.getMessage());
    }
}
