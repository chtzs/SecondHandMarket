package top.hackchen.secondhandmarket.service;

import org.springframework.web.bind.annotation.RequestAttribute;
import top.hackchen.secondhandmarket.beans.Session;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

/**
 * @author wsbch
 * @description 针对表【sessions】的数据库操作Service
 * @createDate 2022-06-06 14:33:29
 */
public interface SessionService extends IService<Session> {
    JsonResult<Object> getSession(Integer buyerId, Integer goodsId);

    JsonResult<Object> createSession(Integer buyerId, Integer goodsId);
}
