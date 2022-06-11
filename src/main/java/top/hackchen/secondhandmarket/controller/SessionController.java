package top.hackchen.secondhandmarket.controller;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.beans.Session;
import top.hackchen.secondhandmarket.service.SessionService;
import top.hackchen.secondhandmarket.util.JsonResult;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @LoginVerify
    @RequestMapping("/get")
    public JsonResult<Object> getSessionId(@RequestAttribute("userId") Integer buyerId, Integer goodsId) {
        JsonResult<Object> re = sessionService.getSession(buyerId, goodsId);
        if (re.isFailed()) return re;
        Integer sessionId = ((Session) re.getData()).getId();
        return JsonResult.success(sessionId);
    }

    @LoginVerify
    @RequestMapping("/create")
    public JsonResult<Object> createSession(@RequestAttribute("userId") Integer buyerId, Integer goodsId) {
        return sessionService.createSession(buyerId, goodsId);
    }
}
