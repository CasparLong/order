package com.caspar.order.entity;

import com.caspar.order.entity.base.BaseDomain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "order_detail")
@Data
public class OrderDetail extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -2228239419926018262L;

    @Id
    @Column(name = "detail_id")
    private String detailId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 当前价格,单位分
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 数量
     */
    @Column(name = "product_quantity")
    private Integer productQuantity;

    /**
     * 小图
     */
    @Column(name = "product_icon")
    private String productIcon;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}