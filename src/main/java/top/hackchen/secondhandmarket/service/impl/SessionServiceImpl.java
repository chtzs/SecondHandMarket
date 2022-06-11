package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.beans.Session;
import top.hackchen.secondhandmarket.service.GoodsService;
import top.hackchen.secondhandmarket.service.SessionService;
import top.hackchen.secondhandmarket.mapper.SessionMapper;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.util.Date;
import java.util.List;

/**
 * @author wsbch
 * @description 针对表【sessions】的数据库操作Service实现
 * @createDate 2022-06-06 14:33:29
 */
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session>
        implements SessionService {
    private final SessionMapper sessionMapper;
    private final GoodsService goodsService;

    public SessionServiceImpl(SessionMapper sessionMapper, GoodsService goodsService) {
        this.sessionMapper = sessionMapper;
        this.goodsService = goodsService;
    }

    @Override
    public JsonResult<Object> getSession(Integer buyerId, Integer goodsId) {
        //先取出商品
        JsonResult<Object> re = goodsService.showDetail(goodsId);
        if (re.isFailed()) return re;
        Goods goods = (Goods) re.getData();
        //检查该商品下，用户是否和卖家已经建立了会话
        Integer sellerId = goods.getSellerId();
        List<Session> sessions = sessionMapper.selectList(
                new QueryWrapper<Session>()
                        .eq("goodsId", goodsId)
                        .eq("sellerId", sellerId)
                        .eq("buyerId", buyerId));
        if (sessions.size() != 1) {
            return JsonResult.SESSION_EXISTED;
        }
        return JsonResult.success(sessions.get(0));
    }

    @Override
    public JsonResult<Object> createSession(Integer buyerId, Integer goodsId) {
        JsonResult<Object> re = getSession(buyerId, goodsId);
        if (re.isFailed()) return re;
        Integer sellerId = ((Goods) re.getData()).getSellerId();
        Session session = new Session();
        session.setGoodsId(goodsId);
        session.setBuyerId(buyerId);
        session.setSellerId(sellerId);
        session.setCreateDate(new Date());
        sessionMapper.insert(session);
        return JsonResult.success("创建成功", session);
    }
}




