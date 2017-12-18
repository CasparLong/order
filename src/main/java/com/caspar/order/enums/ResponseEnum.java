package com.caspar.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-16
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200, "成功"),
    FAIL(400, "失败"),

    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    CART_EMPTY(18, "购物车为空"),

    ;

    private Integer code;
    private String msg;

}
