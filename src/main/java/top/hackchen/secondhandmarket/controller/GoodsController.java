package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.hackchen.secondhandmarket.annotation.AdministrationPrivilege;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.enums.GoodsSortBy;
import top.hackchen.secondhandmarket.enums.SortOrder;
import top.hackchen.secondhandmarket.service.GoodsService;
import top.hackchen.secondhandmarket.util.JsonResult;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/search")
    public JsonResult<Object> searchGoods(IPage<Goods> page, String content,
                                          @RequestParam(defaultValue = "") String column,
                                          @RequestParam(defaultValue = "0") Integer min,
                                          @RequestParam(defaultValue = "0") Integer max,
                                          String order) {
        //如果是范围搜索
        if (column.equals("")) {
            Assert.isTrue(min <= max, "价格最小值不能大于最大值");
            return goodsService.searchGoodsInRange(page, content, min, max, order);
        } else {
            GoodsSortBy sortBy = GoodsSortBy.valueOf(column.toUpperCase());
            SortOrder sortOrder = SortOrder.valueOf(order.toUpperCase());
            return goodsService.searchGoods(page, content, sortBy, sortOrder);
        }
    }

    @RequestMapping("/detail")
    public JsonResult<Object> showDetail(Integer goodsId) {
        return goodsService.showDetail(goodsId);
    }

    @AdministrationPrivilege
    @RequestMapping("/list")
    public JsonResult<IPage<Goods>> list(@RequestParam(defaultValue = "0") Integer current,
                                         @RequestParam(defaultValue = "10") Integer size) {
        IPage<Goods> page = new Page<>(current, size);
        return JsonResult.success(goodsService.page(page));
    }

    @LoginVerify
    @RequestMapping("/post")
    public JsonResult<Object> post(@RequestAttribute Integer userId, Goods goods) {
        goods.setSellerId(userId);
        return goodsService.postGoods(goods);
    }

    @LoginVerify
    @RequestMapping("/delete")
    public JsonResult<Object> delete(@RequestAttribute Integer userId, Integer goodsId) {
        Assert.isTrue(goodsService.goodsBelongTo(userId, goodsId), "该商品不属于你");
        return goodsService.deleteGoods(goodsId);
    }

    @AdministrationPrivilege
    @RequestMapping("/admin/delete")
    public JsonResult<Object> adminDelete(Integer goodsId) {
        return goodsService.deleteGoods(goodsId);
    }

    @LoginVerify
    @RequestMapping("/update")
    public JsonResult<Object> update(@RequestAttribute Integer userId, Goods goods) {
        Assert.isTrue(goodsService.goodsBelongTo(userId, goods.getId()), "该商品不属于你");
        goods.setSellerId(userId);
        return JsonResult.success(goodsService.updateById(goods));
    }

    @AdministrationPrivilege
    @RequestMapping("/admin/update")
    public JsonResult<Object> adminUpdate(Goods goods) {
        return JsonResult.success(goodsService.updateById(goods));
    }

    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    JsonResult<Object> handleConstraintViolationException(Exception e) {
        return JsonResult.failed(JsonResult.BAD_REQUEST, e.getMessage());
    }
}
