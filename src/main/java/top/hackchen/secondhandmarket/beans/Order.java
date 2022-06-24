package top.hackchen.secondhandmarket.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@TableName(value = "orders")
@Data
public class Order implements Serializable {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String orderNumber;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 卖家id
     */
    private Integer buyerId;

    /**
     * 支付时间
     */
    private Date payDate;

    /**
     * 实际付款
     */
    private BigDecimal actualPay;

    /**
     * 商品状态，在运输中，已成功送达，已成功丢失等等
     */
    private Integer deliveryStatus;

    private Integer tradeStatus;

    private String alipayTradeNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}