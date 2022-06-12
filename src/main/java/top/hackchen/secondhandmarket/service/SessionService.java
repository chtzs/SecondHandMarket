package top.hackchen.secondhandmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.beans.Session;
import top.hackchen.secondhandmarket.util.JsonResult;

public interface SessionService extends IService<Session> {
    JsonResult<Object> getSession(Integer buyerId, Integer goodsId);

    JsonResult<Object> createSession(Integer buyerId, Integer goodsId);
}
