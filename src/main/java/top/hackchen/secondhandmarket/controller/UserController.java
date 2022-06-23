package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.hackchen.secondhandmarket.annotation.AdministrationPrivilege;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.annotation.MultiRequestBody;
import top.hackchen.secondhandmarket.annotation.UserPrivilege;
import top.hackchen.secondhandmarket.beans.User;
import top.hackchen.secondhandmarket.service.UserService;
import top.hackchen.secondhandmarket.util.JsonResult;
import top.hackchen.secondhandmarket.util.LoginUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.Objects;

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
            Integer userId = userService.getOne(
                    new QueryWrapper<User>()
                            .eq("phone_number", phoneNumber)).getId();
            return JsonResult.success("登录成功", new Object() {
                public final String token = LoginUtils.createToken(userId);
            });
        } else {
            return JsonResult.WRONG_PASSWORD_OR_USERNAME;
        }
    }

    @LoginVerify
    @RequestMapping("/logOut")
    public JsonResult<Object> logOut(@RequestAttribute Integer userId) {
        return null;
    }

    @RequestMapping("/register")
    public JsonResult<Object> register(HttpServletRequest request, String nickname, Long phoneNumber, String password, String code) {
        HttpSession session = request.getSession();
        String savedCode = (String) session.getAttribute(SMSController.SMS_VERIFICATION_ATTRIBUTE);
        if (!Objects.equals(savedCode, code)) {
            return JsonResult.WRONG_SMS_CAPTCHA;
        }
        return userService.register(nickname, phoneNumber, password);
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
    public JsonResult<Object> update(@RequestAttribute Integer userId, @RequestBody User user) {
        user.setId(userId);
        userService.updateById(user);
        return JsonResult.success("更新成功");
    }

    @AdministrationPrivilege
    @RequestMapping("/admin/update")
    public JsonResult<Object> adminUpdate(@MultiRequestBody User user) {
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

    @LoginVerify
    @RequestMapping("/detail")
    public JsonResult<Object> detail(@RequestAttribute Integer userId) {
        if (userService.isExist(userId)) {
            return JsonResult.success(userService.getById(userId));
        }
        return JsonResult.USER_NOT_EXIST;
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
