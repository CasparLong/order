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
        this(responseEnum.getCode(), responseEnum.getMsg());
    }

    public SellException(Integer codeString, String message) {
        super(message);
        this.code = code;
    }
}
