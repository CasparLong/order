package com.caspar.order.service.impl;

import com.caspar.order.entity.OrderDetail;
import com.caspar.order.mapper.OrderDetailMapper;
import com.caspar.order.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
@Service
@Slf4j
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> selectByOrderId(String orderId) {
        OrderDetail queryEntity = new OrderDetail();
        queryEntity.setOrderId(orderId);
        return orderDetailMapper.select(queryEntity);
    }
}
