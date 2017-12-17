package com.caspar.order.service;

import com.caspar.order.dto.OrderDTO;
import com.caspar.order.entity.OrderMaster;
import com.caspar.order.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
public interface OrderMasterService extends BaseService<OrderMaster> {

    /**
     * 根据openId查询订单
     *
     * @param buyerOpenid
     * @return
     */
    List<OrderMaster> findByBuyerOpenid(String buyerOpenid);

    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(Pageable pageable);

}
