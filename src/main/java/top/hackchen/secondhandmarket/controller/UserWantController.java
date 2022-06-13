package top.hackchen.secondhandmarket.controller;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.service.UserWantService;
import top.hackchen.secondhandmarket.util.JsonResult;

@RestController
@RequestMapping("/api/want")
public class UserWantController {
    private final UserWantService userWantService;

    public UserWantController(UserWantService userWantService) {
        this.userWantService = userWantService;
    }

    @LoginVerify
    @RequestMapping("/want")
    public JsonResult<Object> want(@RequestAttribute Integer userId, Integer goodsId) {
        return userWantService.want(userId, goodsId);
    }

    @LoginVerify
    @RequestMapping("/remove")
    public JsonResult<Object> removeWant(@RequestAttribute Integer userId, Integer goodsId) {
        return userWantService.removeWant(userId, goodsId);
    }
}
