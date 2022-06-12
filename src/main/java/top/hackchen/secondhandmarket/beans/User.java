package top.hackchen.secondhandmarket.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@TableName(value = "users")
@Data
public class User implements Serializable {
    /**
     * 内部id，唯一
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    private Long phoneNumber;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 性别
     */
    private String gender;

    /**
     * 用户权限，0是用户，1是管理员
     */
    private Integer identity;

    /**
     * 注册时间
     */
    private Date registerDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}