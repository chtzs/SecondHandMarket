package top.hackchen.secondhandmarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import top.hackchen.secondhandmarket.util.JsonResult;


@RestController
@RequestMapping("/api/order")
public class OrderController {
    public void pay(Integer orderId) {
    }
}
