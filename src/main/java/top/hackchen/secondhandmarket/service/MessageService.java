package top.hackchen.secondhandmarket.service;

import top.hackchen.secondhandmarket.beans.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

/**
* @author wsbch
* @description 针对表【messages】的数据库操作Service
* @createDate 2022-06-06 14:33:29
*/
public interface MessageService extends IService<Message> {
    JsonResult<Object> sendMessage(Integer senderId, Integer sessionId, String content);
}
