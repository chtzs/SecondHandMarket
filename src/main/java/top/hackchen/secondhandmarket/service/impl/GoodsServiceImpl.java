package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.beans.Review;
import top.hackchen.secondhandmarket.beans.UserWant;
import top.hackchen.secondhandmarket.enums.GoodsSortBy;
import top.hackchen.secondhandmarket.enums.SortOrder;
import top.hackchen.secondhandmarket.mapper.ReviewMapper;
import top.hackchen.secondhandmarket.mapper.UserWantMapper;
import top.hackchen.secondhandmarket.service.GoodsService;
import top.hackchen.secondhandmarket.mapper.GoodsMapper;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.service.UserService;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author wsbch
 * @description 针对表【goods】的数据库操作Service实现
 * @createDate 2022-06-06 14:33:29
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {
    private final UserService userService;
    private final GoodsMapper goodsMapper;
    private final ReviewMapper reviewMapper;
    private final UserWantMapper userWantMapper;

    public GoodsServiceImpl(UserService userService, GoodsMapper goodsMapper,
                            ReviewMapper reviewMapper, UserWantMapper userWantMapper) {
        this.userService = userService;
        this.goodsMapper = goodsMapper;
        this.reviewMapper = reviewMapper;
        this.userWantMapper = userWantMapper;
    }

    @Override
    public boolean goodsExist(Integer goodsId) {
        List<Goods> goods = goodsMapper.selectList(new QueryWrapper<Goods>().eq("id", goodsId));
        return goods.size() == 1;
    }

    @Override
    public boolean goodsBelongTo(Integer sellerId, Integer goodsId) {
        JsonResult<Object> re = showDetail(goodsId);
        //检查是否查询失败
        if (!Objects.equals(re.getCode(), JsonResult.DEFAULT_SUCCESS_CODE)) return false;
        //然后检查一下该商品是否属于该用户
        Goods goods = (Goods) re.getData();
        return Objects.equals(goods.getSellerId(), sellerId);
    }

    @Override
    public JsonResult<Object> postGoods(Goods goods) {
        //如果一边销号一边发布就会出错，毕竟数据库里面已经没有这个键了
        //不过应该是会报服务器内部错误，无所谓了，不需要特殊判断处理
        if (userService.isExist(goods.getSellerId())) {
            goods.setPostDate(new Date());
            save(goods);
            return JsonResult.success(goods.getId());
        }
        return JsonResult.USER_NOT_EXIST;
    }

    @Override
    public JsonResult<Object> deleteGoods(Integer goodsId) {
        //删除外键依赖关系
        reviewMapper.delete(new QueryWrapper<Review>().eq("goods_id", goodsId));
        userWantMapper.delete(new QueryWrapper<UserWant>().eq("goods_id", goodsId));
        goodsMapper.deleteById(goodsId);
        return JsonResult.success("删除成功");
    }

    @Override
    public JsonResult<Object> searchGoods(IPage<Goods> page, String content,
                                          GoodsSortBy column, SortOrder method) {
        IPage<Goods> goods = goodsMapper.searchGoods(page, "%" + content + "%", column.getColumn(), method.getOrder());
        return JsonResult.success(goods);
    }

    @Override
    public JsonResult<Object> searchGoodsInRange(IPage<Goods> page, String content, Integer min, Integer max, String order) {
        IPage<Goods> goods = goodsMapper.searchGoodsInRange(page, content, min, max, order);
        return JsonResult.success(goods);
    }

    @Override
    public JsonResult<Object> showDetail(Integer goodsId) {
        if (goodsExist(goodsId)) {
            return JsonResult.success(goodsMapper.selectById(goodsId));
        }
        return JsonResult.GOODS_NOT_EXIST;
    }
}




