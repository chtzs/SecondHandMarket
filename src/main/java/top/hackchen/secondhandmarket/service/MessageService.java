package top.hackchen.secondhandmarket.service;

import top.hackchen.secondhandmarket.beans.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

public interface MessageService extends IService<Message> {
    JsonResult<Object> sendMessage(Integer senderId, Integer sessionId, String content);
}
