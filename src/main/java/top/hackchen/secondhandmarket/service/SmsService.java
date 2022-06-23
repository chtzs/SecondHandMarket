package top.hackchen.secondhandmarket.service;

import top.hackchen.secondhandmarket.util.JsonResult;

public interface SmsService {
    JsonResult<Object> sendMessage(String phoneNumber, String... params);
}
