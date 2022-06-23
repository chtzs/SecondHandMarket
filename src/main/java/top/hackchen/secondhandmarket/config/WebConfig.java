package top.hackchen.secondhandmarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.hackchen.secondhandmarket.interceptor.AuthenticationInterceptor;
import top.hackchen.secondhandmarket.interceptor.MultiRequestBodyArgumentResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final MultiRequestBodyArgumentResolver multiRequestBodyArgumentResolver;

    public WebConfig(AuthenticationInterceptor authenticationInterceptor, MultiRequestBodyArgumentResolver multiRequestBodyArgumentResolver) {
        this.authenticationInterceptor = authenticationInterceptor;
        this.multiRequestBodyArgumentResolver = multiRequestBodyArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 添加MultiRequestBody参数解析器
        argumentResolvers.add(multiRequestBodyArgumentResolver);
    }

    /**
     * 为整个项目添加一个拦截器，因此在所有请求前我们可以处理一些通用配置信息
     *
     * @param registry 拦截器注册类
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加我们自己的身份验证拦截器，拦截一切请求（包括静态资源？）
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/alipay/**");
    }

    /**
     * 跨域设置，如果不配置，浏览器将拦截一切网络请求
     *
     * @param registry 拦截器注册类
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
                .allowedOriginPatterns("*")
                //放行全部请求方式
                .allowedMethods("*")
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些原始请求头部信息
                .exposedHeaders("*");
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
