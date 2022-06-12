package top.hackchen.secondhandmarket.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@TableName(value = "messages")
@Data
public class Message implements Serializable {
    /**
     * 消息id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 会话id
     */
    private Integer sessionId;

    /**
     * 发送方id
     */
    private Integer senderId;

    /**
     * 接收方id
     */
    private Integer receiverId;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 创建日期
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}