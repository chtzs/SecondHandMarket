package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hackchen.secondhandmarket.beans.Message;
import top.hackchen.secondhandmarket.beans.Session;
import top.hackchen.secondhandmarket.mapper.SessionMapper;
import top.hackchen.secondhandmarket.service.MessageService;
import top.hackchen.secondhandmarket.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.util.JsonResult;

import java.util.*;

/**
 * @author wsbch
 * @description 针对表【messages】的数据库操作Service实现
 * @createDate 2022-06-06 14:33:29
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {
    private final SessionMapper sessionMapper;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(SessionMapper sessionMapper, MessageMapper messageMapper) {
        this.sessionMapper = sessionMapper;
        this.messageMapper = messageMapper;
    }

    @Override
    public JsonResult<Object> sendMessage(Integer senderId, Integer sessionId, String content) {
        //检查会话id是否存在
        List<Session> sessions =
                sessionMapper.selectList(
                        new QueryWrapper<Session>()
                                .eq("id", senderId));
        if (sessions.size() != 1) return JsonResult.SESSION_NOT_EXIST;
        Session session = sessions.get(0);
        //检查发送方和接收方id是否和会话信息一致
        Integer a1 = session.getBuyerId(), a2 = session.getSellerId();
        Integer receiver = Objects.equals(a1, senderId) ? a2 : a1;
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiver);
        message.setContent(content);
        message.setCreateDate(new Date());
        messageMapper.insert(message);
        return JsonResult.success("发送成功", message);
    }
}




