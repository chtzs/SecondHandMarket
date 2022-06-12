package top.hackchen.secondhandmarket.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@TableName(value ="reviews")
@Data
public class Review implements Serializable {
    /**
     * 审核id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 被审核的商品id
     */
    private Integer goodsId;

    /**
     * 管理员审核决定，0为通过，1为拒绝，2为审核中
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}