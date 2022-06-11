package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.hackchen.secondhandmarket.annotation.AdministrationPrivilege;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.annotation.UserPrivilege;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.beans.User;
import top.hackchen.secondhandmarket.service.UserService;
import top.hackchen.secondhandmarket.util.JsonResult;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;

@RequestMapping("/api/user")
@RestController
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public JsonResult<Object> login(Long phoneNumber, String password) {
        if (userService.checkPasswordValid(phoneNumber, password)) {
            //TODO: 生成Token
            return JsonResult.success("登录成功");
        } else {
            return JsonResult.WRONG_PASSWORD_OR_USERNAME;
        }
    }

    @LoginVerify
    @RequestMapping("/logOut")
    public JsonResult<Object> logOut(@RequestAttribute Integer userId) {
        //TODO: 删除token
        return null;
    }

    @RequestMapping("/register")
    public JsonResult<Object> register(Long phoneNumber, String password) {
        //TODO: 手机验证
        return userService.register(phoneNumber, password);
    }

    @UserPrivilege
    @RequestMapping("/delete")
    public JsonResult<Object> delete(@RequestAttribute Integer userId) {
        if (userService.isExist(userId)) {
            return userService.fuckOff(userId);
        }
        return JsonResult.USER_NOT_EXIST;
    }

    @AdministrationPrivilege
    @RequestMapping("/admin/delete")
    public JsonResult<Object> adminDelete(@RequestParam Integer userId) {
        return delete(userId);
    }

    public JsonResult<Object> modifyPassword() {
        //TODO: 手机验证
        return null;
    }

    @UserPrivilege
    @RequestMapping("/update")
    public JsonResult<Object> update(@RequestAttribute Integer userId, @RequestParam User user) {
        user.setId(userId);
        userService.updateById(user);
        return JsonResult.success("更新成功");
    }

    @AdministrationPrivilege
    @RequestMapping("/admin/update")
    public JsonResult<Object> adminUpdate(User user) {
        Assert.notNull(user, "参数不能为null");
        Assert.notNull(user.getId(), "用户id不能为null");

        if (userService.isExist(user.getId())) {
            userService.updateById(user);
            return JsonResult.success("更新成功");
        }
        return JsonResult.USER_NOT_EXIST;
    }

    @AdministrationPrivilege
    @RequestMapping("/search")
    public JsonResult<IPage<User>> searchUser(@RequestParam(defaultValue = "0") Integer current,
                                              @RequestParam(defaultValue = "10") Integer size,
                                              String content) {
        return userService.searchAll(new Page<>(current, size), content);
    }

    @AdministrationPrivilege
    @RequestMapping("/list")
    public JsonResult<IPage<User>> list(@RequestParam(defaultValue = "0") Integer current,
                                         @RequestParam(defaultValue = "10") Integer size) {
        IPage<User> page = new Page<>(current, size);
        return JsonResult.success(userService.page(page));
    }

    @AdministrationPrivilege
    @RequestMapping("/test")
    public JsonResult<Object> test(@Min(10) Integer number) {
        return JsonResult.success("访问成功");
    }

    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    JsonResult<Object> handleConstraintViolationException(Exception e) {
        return JsonResult.failed(JsonResult.BAD_REQUEST, e.getMessage());
    }
}
