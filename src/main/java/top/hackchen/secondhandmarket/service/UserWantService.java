package top.hackchen.secondhandmarket.service;

import top.hackchen.secondhandmarket.beans.UserWant;
import com.baomidou.mybatisplus.extension.service.IService;
import top.hackchen.secondhandmarket.util.JsonResult;

public interface UserWantService extends IService<UserWant> {
    boolean wantExist(Integer userId, Integer goodsId);

    JsonResult<Object> want(Integer userId, Integer goodsId);

    JsonResult<Object> removeWant(Integer userId, Integer goodsId);
}
