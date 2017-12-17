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

    CART_EMPTY(18, "购物车为空"),
    ;

    private Integer code;
    private String msg;

}
