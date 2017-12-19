package com.caspar.order.exception;

import com.caspar.order.enums.ResponseEnum;
import lombok.Getter;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-17
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResponseEnum responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMessage());
    }

    public SellException(Integer codeString, String message) {
        super(message);
        this.code = code;
    }
}
