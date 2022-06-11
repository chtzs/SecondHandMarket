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
import top.hackchen.secondhandmarket.util.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;

    public AuthenticationInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
            //TODO: 判断Token是否在服务器上，如果不在就返回failed
            int status = 0;
            if (adminOnly) {
                //葡萄🍇用户访问或操作管理员才能操作的页面
                if (status == 0) {
                    response.getWriter().print(objectMapper.writeValueAsString(JsonResult.ACCESS_DENIED));
                    return false;
                }
            }
            //TODO: 替换成真正的userId
            request.setAttribute("userId", 10);
            request.setAttribute("user", null);
            return true;
        }
        return true;
    }
}
