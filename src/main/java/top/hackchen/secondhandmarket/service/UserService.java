package top.hackchen.secondhandmarket.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.hackchen.secondhandmarket.beans.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

/**
 * @author wsbch
 * @description 针对表【users】的数据库操作Service
 * @createDate 2022-06-06 14:33:29
 */
public interface UserService extends IService<User> {
    boolean checkPasswordValid(Long phoneNumber, String password);

    boolean notExist(Long phoneNumber);

    boolean isExist(Integer userId);

    JsonResult<IPage<User>> searchAll(IPage<User> page, String content);

    JsonResult<Object> register(Long phoneNumber, String password);

    JsonResult<Object> fuckOff(int userId);
}
