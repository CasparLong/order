package com.caspar.order.dto;

import com.caspar.order.entity.OrderDetail;
import com.caspar.order.entity.OrderMaster;
import com.caspar.order.enums.OrderStatusEnum;
import com.caspar.order.enums.PayStatusEnum;
import com.caspar.order.util.EnumUtil;
import com.caspar.order.util.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Converter;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-17
 */
@Data
@NoArgsConstructor
@Builder
public class OrderDTO {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信Openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

    public OrderMaster convertToOrderMaster() {
        OrderDTOConverter orderDTOConverter = new OrderDTOConverter();
        return orderDTOConverter.convert(this);
    }

    public static OrderDTO convertFor(OrderMaster orderMaster) {
        OrderDTOConverter orderDTOConverter = new OrderDTOConverter();
        return orderDTOConverter.reverse().convert(orderMaster);
    }

    private static class OrderDTOConverter extends Converter<OrderDTO, OrderMaster> {
        @Override
        protected OrderMaster doForward(OrderDTO orderDTO) {
            OrderMaster orderMaster = new OrderMaster();
            BeanUtils.copyProperties(orderDTO, orderMaster);
            return orderMaster;
        }

        @Override
        protected OrderDTO doBackward(OrderMaster orderMaster) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            return orderDTO;
        }
    }

}
