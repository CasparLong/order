package com.caspar.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-17
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements CodeEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

}
