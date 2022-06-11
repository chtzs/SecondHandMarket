package top.hackchen.secondhandmarket.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName bids
 */
@TableName(value ="bids")
@Data
public class Bid implements Serializable {
    /**
     * 报价id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 卖家id
     */
    private Integer buyerId;

    /**
     * 报价
     */
    private BigDecimal offer;

    /**
     * 报价创建时间
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}