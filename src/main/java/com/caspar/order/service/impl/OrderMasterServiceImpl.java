package com.caspar.order.service.impl;

import com.caspar.order.dto.OrderDTO;
import com.caspar.order.entity.OrderMaster;
import com.caspar.order.mapper.OrderMasterMapper;
import com.caspar.order.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<OrderMaster> findByBuyerOpenid(String buyerOpenid) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid(buyerOpenid);

        return orderMasterMapper.select(orderMaster);
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
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
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }

}
