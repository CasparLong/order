package com.caspar.order.service.impl;

import com.caspar.order.dto.CartDTO;
import com.caspar.order.dto.OrderDTO;
import com.caspar.order.entity.OrderDetail;
import com.caspar.order.entity.OrderMaster;
import com.caspar.order.entity.ProductInfo;
import com.caspar.order.enums.OrderStatusEnum;
import com.caspar.order.enums.PayStatusEnum;
import com.caspar.order.enums.ResponseEnum;
import com.caspar.order.exception.SellException;
import com.caspar.order.mapper.OrderMasterMapper;
import com.caspar.order.service.OrderDetailService;
import com.caspar.order.service.OrderMasterService;
import com.caspar.order.service.ProductInfoService;
import com.caspar.order.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
@Service
@Slf4j
public class OrderMasterServiceImpl extends BaseServiceImpl<OrderMaster> implements OrderMasterService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public List<OrderMaster> findByBuyerOpenid(String buyerOpenid) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid(buyerOpenid);

        return orderMasterMapper.select(orderMaster);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = IdGenerator.generate().toString();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 查询商品数量、价格
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.selectByProductId(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResponseEnum.PRODUCT_NOT_EXIST);
            }

            // 计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 订单详情入库
            orderDetail.setDetailId(IdGenerator.generate().toString());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailService.save(orderDetail);
        }

        // 订单入库
        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = orderDTO.convertToOrderMaster();
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterMapper.insertSelective(orderMaster);

        // 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        //TODO:发送WebSocket消息

        return orderDTO;
    }

    @Override
    public OrderDTO selectByOrderId(String orderId) {
        OrderMaster queryEntity = new OrderMaster();
        queryEntity.setOrderId(orderId);
        OrderMaster orderMaster = this.oneSelect(queryEntity);

        if (orderMaster == null) {
            throw new SellException(ResponseEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailService.selectByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResponseEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = OrderDTO.convertFor(orderMaster);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public List<OrderDTO> selectByBuyerOpenId(String buyerOpenid) {
        OrderMaster queryEntity = new OrderMaster();
        queryEntity.setBuyerOpenid(buyerOpenid);
        List<OrderMaster> orderMasterList = this.select(queryEntity);

        List<OrderDTO> orderDTOList = orderMasterList.stream().map(e -> OrderDTO.convertFor(e)).collect(Collectors.toList());

        return orderDTOList;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> selectByBuyerOpenId(Pageable pageable) {
        return null;
    }

}
