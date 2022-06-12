package top.hackchen.secondhandmarket.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.hackchen.secondhandmarket.beans.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

public interface UserService extends IService<User> {
    boolean checkPasswordValid(Long phoneNumber, String password);

    boolean notExist(Long phoneNumber);

    boolean isExist(Integer userId);

    JsonResult<IPage<User>> searchAll(IPage<User> page, String content);

    JsonResult<Object> register(Long phoneNumber, String password);

    JsonResult<Object> fuckOff(int userId);
}
