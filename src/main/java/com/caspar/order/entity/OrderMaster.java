package com.caspar.order.entity;

import com.caspar.order.entity.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "order_master")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderMaster extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -6962487582165693542L;

    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 买家名字
     */
    @Column(name = "buyer_name")
    private String buyerName;

    /**
     * 买家电话
     */
    @Column(name = "buyer_phone")
    private String buyerPhone;

    /**
     * 买家地址
     */
    @Column(name = "buyer_address")
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    @Column(name = "buyer_openid")
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为新下单
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 支付状态, 默认未支付
     */
    @Column(name = "pay_status")
    private Integer payStatus;

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