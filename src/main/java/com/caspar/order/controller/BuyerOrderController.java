package com.caspar.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.caspar.order.converter.OrderForm2OrderDTOConverter;
import com.caspar.order.dto.OrderDTO;
import com.caspar.order.enums.ResponseEnum;
import com.caspar.order.exception.SellException;
import com.caspar.order.form.OrderForm;
import com.caspar.order.response.ResponseBuilder;
import com.caspar.order.response.dto.Response;
import com.caspar.order.service.OrderMasterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Description:买家订单
 *
 * @author Caspar
 * @Date 2017-12-18
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public Response createOrder(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建订单:参数有误,orderForm={}", orderForm);
            throw new SellException(ResponseEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("创建订单:购物车为空");
            throw new SellException(ResponseEnum.CART_EMPTY);
        }

        orderDTO = orderMasterService.create(orderDTO);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderDTO.getOrderId());

        return ResponseBuilder.buildSuccess(jsonObject);
    }

    @GetMapping("/list")
    public Response list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isBlank(openid)) {
            log.error("获取订单列表:openid为空");
            throw new SellException(ResponseEnum.PARAM_ERROR);
        }

        PageHelper.startPage(page, size);
        List<OrderDTO> orderDTOList = orderMasterService.selectByBuyerOpenId(openid);
        PageInfo<OrderDTO> pageInfo = new PageInfo<>(orderDTOList, 5);

        return ResponseBuilder.buildSuccess(pageInfo);
    }

}
