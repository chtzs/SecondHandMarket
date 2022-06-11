package top.hackchen.secondhandmarket.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.hackchen.secondhandmarket.beans.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.enums.GoodsSortBy;
import top.hackchen.secondhandmarket.enums.SortOrder;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.math.BigDecimal;

/**
 * @author wsbch
 * @description 针对表【goods】的数据库操作Service
 * @createDate 2022-06-06 14:33:29
 */
public interface GoodsService extends IService<Goods> {
    boolean goodsExist(Integer goodsId);

    boolean goodsBelongTo(Integer sellerId, Integer goodsId);

    JsonResult<Object> postGoods(Goods goods);

    JsonResult<Object> deleteGoods(Integer goodsId);

    JsonResult<Object> searchGoods(IPage<Goods> page, String content, GoodsSortBy column, SortOrder method);

    JsonResult<Object> searchGoodsInRange(IPage<Goods> page, String content, Integer min, Integer max, String order);

    JsonResult<Object> showDetail(Integer goodsId);
}
