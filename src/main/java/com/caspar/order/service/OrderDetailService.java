package com.caspar.order.service;

import com.caspar.order.entity.OrderDetail;
import com.caspar.order.service.base.BaseService;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
public interface OrderDetailService extends BaseService<OrderDetail> {

    List<OrderDetail> selectByOrderId(String orderId);

}
