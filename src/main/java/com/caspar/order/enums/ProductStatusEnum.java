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
public enum  ProductStatusEnum implements CodeEnum{

    UP(0,"在架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

}
