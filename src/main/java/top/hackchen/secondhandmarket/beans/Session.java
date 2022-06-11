package top.hackchen.secondhandmarket.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sessions
 */
@TableName(value ="sessions")
@Data
public class Session implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer goodsId;

    /**
     * 
     */
    private Integer buyerId;

    /**
     * 
     */
    private Integer sellerId;

    /**
     * 
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}