package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.beans.Message;
import top.hackchen.secondhandmarket.beans.Session;
import top.hackchen.secondhandmarket.service.MessageService;
import top.hackchen.secondhandmarket.service.SessionService;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/msg")
public class MessageController {
    private final MessageService messageService;
    private final SessionService sessionService;

    public MessageController(MessageService messageService, SessionService sessionService) {
        this.messageService = messageService;
        this.sessionService = sessionService;
    }

    @LoginVerify
    @RequestMapping("/send")
    public JsonResult<Object> sendMessage(@RequestAttribute("userId") Integer senderId,
                                          Integer sessionId, String content) {
        return messageService.sendMessage(senderId, sessionId, content);
    }

    @LoginVerify
    @RequestMapping("/list")
    public JsonResult<List<Message>> listMessage(@RequestAttribute Integer userId,
                                                 Integer sessionId) {
        Session session = sessionService.getById(sessionId);
        Assert.isTrue(Objects.equals(session.getBuyerId(), userId)
                || Objects.equals(session.getSellerId(), userId), "用户和会话方不匹配");
        List<Message> list = messageService.list(new QueryWrapper<Message>()
                .eq("session_id", sessionId)
                .orderByAsc("create_date"));
        return JsonResult.success(list);
    }
}
