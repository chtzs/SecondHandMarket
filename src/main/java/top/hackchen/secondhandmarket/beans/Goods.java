package top.hackchen.secondhandmarket.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@TableName(value = "goods")
@Data
public class Goods implements Serializable {
    /**
     * 商品编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 卖家id
     */
    private Integer sellerId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 原价
     */
    private BigDecimal fakePrice;

    /**
     * 标价
     */
    private BigDecimal actualPrice;

    /**
     * 功能状态
     */
    private String functionality;

    /**
     * 成色(condition是mysql的关键词啊啊啊可恶查了半天的bug岂可休岂可休)
     */
    private String goodsCondition;

    /**
     * 发布日期
     */
    private Date postDate;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 被想要次数
     */
    private Integer wantedCount;

    /**
     * 审核中，已发布，已卖出，等等
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}