package top.hackchen.secondhandmarket.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.hackchen.secondhandmarket.annotation.AdministrationPrivilege;
import top.hackchen.secondhandmarket.annotation.LoginVerify;
import top.hackchen.secondhandmarket.annotation.UserPrivilege;
import top.hackchen.secondhandmarket.beans.User;
import top.hackchen.secondhandmarket.mapper.UserMapper;
import top.hackchen.secondhandmarket.util.EncryptUtils;
import top.hackchen.secondhandmarket.util.JsonResult;
import top.hackchen.secondhandmarket.util.LoginUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;

    public AuthenticationInterceptor(ObjectMapper objectMapper, UserMapper userMapper) {
        this.objectMapper = objectMapper;
        this.userMapper = userMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        boolean loginOnly = method.isAnnotationPresent(UserPrivilege.class)
                || method.isAnnotationPresent(LoginVerify.class);
        boolean adminOnly = method.isAnnotationPresent(AdministrationPrivilege.class);
        boolean needLogin = loginOnly || adminOnly;
        //验证登录注解
        if (needLogin) {
            //从 http 请求头中取出 token
            String token = request.getHeader("token");
            //验证token是否正确
            if (!LoginUtils.verifyToken(token)) {
                writeData(response, JsonResult.ACCESS_DENIED);
                return false;
            }
            Integer id = Integer.valueOf(LoginUtils.getId(token));
            User user = userMapper.selectById(id);
            int status = user.getIdentity();
            if (adminOnly) {
                //葡萄🍇用户访问或操作管理员才能操作的页面
                if (status == 0) {
                    writeData(response, JsonResult.ACCESS_DENIED);
                    return false;
                }
            }
            //传递给controller
            request.setAttribute("userId", id);
            request.setAttribute("user", user);
            return true;
        }
        return true;
    }

    private <T> void writeData(HttpServletResponse response, JsonResult<T> result) throws IOException {
        response.getWriter().print(objectMapper.writeValueAsString(result));
    }
}
