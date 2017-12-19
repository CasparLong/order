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
    JSON_PARSE_ERROR(401, "JSON解析出错"),
    SYSTEM_ERROR(900, "系统繁忙"),
    ILLEGAL_ARGUMENT(401,"非法参数"),
    PROGRAM_ERROR(900,"程序打包异常,请联系管理员"),


    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    CART_EMPTY(18, "购物车为空"),

    ;

    private Integer code;
    private String message;

}
