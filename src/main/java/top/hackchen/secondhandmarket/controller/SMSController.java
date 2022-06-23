package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hackchen.secondhandmarket.service.SmsService;
import top.hackchen.secondhandmarket.service.UserService;
import top.hackchen.secondhandmarket.util.JsonResult;
import top.hackchen.secondhandmarket.util.RandomUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/sms")
public class SMSController {
    private static final String REGEX_MOBILE_EXACT = "^((13\\d)|(14[5,7])|(15[0-3,5-9])|(17[0,35-8])|(18\\d)|(147))\\d{8}$";
    private static final Pattern PATTERN_REGEX_MOBILE_EXACT = Pattern.compile(REGEX_MOBILE_EXACT);
    private static final String EFFECTIVE_MINUS = "5";
    public static final String SMS_VERIFICATION_ATTRIBUTE = "SMS_VERIFICATION_ATTRIBUTE";
    private final SmsService smsService;
    private final UserService userService;

    public SMSController(SmsService smsService, UserService userService) {
        this.smsService = smsService;
        this.userService = userService;
    }

    @RequestMapping("/send")
    public JsonResult<Object> sendMessage(HttpServletRequest request, String captcha, String phoneNumber) {
        if (!isMobile(phoneNumber)) {
            return JsonResult.WRONG_PHONE;
        }
        if (userService.isExist(phoneNumber)) {
            return JsonResult.USER_EXISTED;
        }
        HttpSession session = request.getSession();
        if (!Objects.equals(captcha, session.getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            return JsonResult.WRONG_CAPTCHA;
        }
        String code = RandomUtils.getSixBitRandom();
        JsonResult<Object> result = smsService.sendMessage(phoneNumber, code, EFFECTIVE_MINUS);
        if (result.isSuccessful()) {
            session.setAttribute(SMS_VERIFICATION_ATTRIBUTE, code);
        }
        return result;
    }

    private static boolean isMobile(String str) {
        return StringUtils.isNotEmpty(str) && SMSController.PATTERN_REGEX_MOBILE_EXACT.matcher(str).matches();
    }
}
