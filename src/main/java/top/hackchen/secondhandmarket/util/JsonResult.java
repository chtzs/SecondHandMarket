package top.hackchen.secondhandmarket.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;

@Data
public class JsonResult<T> {
    //1000：访问相关
    public static JsonResult<Object> ACCESS_DENIED = new JsonResult<>(1001, "访问失败，没有相关权限");
    //2000：用户相关
    public static JsonResult<Object> WRONG_PASSWORD_OR_USERNAME = new JsonResult<>(2001, "用户名或者密码错误");
    public static JsonResult<Object> USER_EXISTED = new JsonResult<>(2002, "用户已经存在");
    public static JsonResult<Object> USER_NOT_EXIST = new JsonResult<>(2003, "用户不存在");
    //3000: 商品相关
    public static JsonResult<Object> GOODS_NOT_EXIST = new JsonResult<>(3001, "商品不存在");
    public static JsonResult<Object> WANT_NOT_EXIST = new JsonResult<>(3002, "您没有想要过这件商品");
    public static JsonResult<Object> WANT_EXISTED = new JsonResult<>(3003, "商品已被想要过");
    //4000: 会话相关
    public static JsonResult<Object> SESSION_NOT_EXIST = new JsonResult<>(4001, "会话不存在");
    public static JsonResult<Object> SESSION_EXISTED = new JsonResult<>(4002, "会话已经存在");
    public static JsonResult<Object> INCOMPATIBLE_SENDER_RECEIVER = new JsonResult<>(4003, "发送方和接收方不一致");
    public static final int BAD_REQUEST = 5001;

    public static final String DEFAULT_SUCCESS = "success";
    public static final String DEFAULT_FAILED = "failed";
    //失败的情况可能有很多，但是成功的情况只有一个！
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    private Integer code;
    private String msg;
    private T data;

    private JsonResult(Integer code, String msg) {
        this(code, msg, null);
    }

    private JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<>(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS, data);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<>(DEFAULT_SUCCESS_CODE, msg, data);
    }

    public static <T> JsonResult<T> success(String msg) {
        return new JsonResult<>(DEFAULT_SUCCESS_CODE, msg, null);
    }

    public static <T> JsonResult<T> failed(Integer code, String msg) {
        return new JsonResult<>(code, msg, null);
    }

    public static <T> JsonResult<T> failed(Integer code, T data) {
        return new JsonResult<>(code, DEFAULT_FAILED, data);
    }

    public static <T> JsonResult<T> build(Integer code, String msg, T data) {
        return new JsonResult<>(code, msg, data);
    }

    @JsonIgnore
    public boolean isFailed() {
        return !Objects.equals(code, DEFAULT_SUCCESS_CODE);
    }

    @JsonIgnore
    public boolean isSuccessful() {
        return Objects.equals(code, DEFAULT_SUCCESS_CODE);
    }
}
