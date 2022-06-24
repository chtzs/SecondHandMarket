package top.hackchen.secondhandmarket.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.kms.aliyun.http.HttpResponse;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.service.PayService;
import top.hackchen.secondhandmarket.util.EncryptUtils;
import top.hackchen.secondhandmarket.util.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.alipay.api.AlipayConstants.*;

@RestController
@RequestMapping("/api/pay")
public class AlipayController {
    @Autowired
    private PayService payService;

    @LoginVerify
    @RequestMapping("/pay")
    public JsonResult<String> pay(Integer orderId) {
        return JsonResult.success("成功", payService.generatePayForm(orderId));
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public void payNotify(@RequestParam("out_trade_no") Integer orderId,
                          @RequestParam("trade_no") String tradeNumber,
                          @RequestParam("trade_status") String status) {
        if (Objects.equals(status, "TRADE_SUCCESS")) {
            payService.notify(orderId, tradeNumber);
        }
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    JsonResult<Object> handleConstraintViolationException(Exception e) {
        e.printStackTrace();
        return JsonResult.failed(JsonResult.BAD_REQUEST, e.getMessage());
    }
}
