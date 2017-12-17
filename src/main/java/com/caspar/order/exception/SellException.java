package com.caspar.order.exception;

import com.caspar.order.enums.ResponseEnum;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-17
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResponseEnum responseEnum) {
        this(responseEnum.getMsg(), responseEnum.getCode());
    }

    public SellException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
