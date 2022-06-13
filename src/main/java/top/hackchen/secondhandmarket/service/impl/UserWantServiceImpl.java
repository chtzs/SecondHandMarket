package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.beans.UserWant;
import top.hackchen.secondhandmarket.mapper.GoodsMapper;
import top.hackchen.secondhandmarket.service.UserWantService;
import top.hackchen.secondhandmarket.mapper.UserWantMapper;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.util.JsonResult;

@Service
public class UserWantServiceImpl extends ServiceImpl<UserWantMapper, UserWant>
        implements UserWantService {
    private final UserWantMapper userWantMapper;
    private final GoodsMapper goodsMapper;

    private final Object lock = new Object();

    public UserWantServiceImpl(UserWantMapper userWantMapper, GoodsMapper goodsMapper) {
        this.userWantMapper = userWantMapper;
        this.goodsMapper = goodsMapper;
    }

    public void addWantCount(Integer goodsId) {
        synchronized (lock) {
            Goods goods = goodsMapper.selectById(goodsId);
            Integer count = goods.getWantedCount();
            goods.setWantedCount(count + 1);
            goodsMapper.updateById(goods);
        }
    }

    @Override
    public boolean wantExist(Integer userId, Integer goodsId) {
        return userWantMapper.exists(
                new QueryWrapper<UserWant>()
                        .eq("user_id", userId)
                        .eq("goods_id", goodsId)
        );
    }

    @Override
    public JsonResult<Object> want(Integer userId, Integer goodsId) {
        if (!wantExist(userId, goodsId)) {
            synchronized (lock) {
                if (!wantExist(userId, goodsId)) {
                    UserWant want = new UserWant();
                    want.setUserId(userId);
                    want.setGoodsId(goodsId);
                    userWantMapper.insert(want);
                    addWantCount(goodsId);
                }
            }
        }
        return JsonResult.WANT_EXISTED;
    }

    @Override
    public JsonResult<Object> removeWant(Integer userId, Integer goodsId) {
        if (wantExist(userId, goodsId)) {
            return JsonResult.WANT_NOT_EXIST;
        }
        return JsonResult.WANT_EXISTED;
    }
}




