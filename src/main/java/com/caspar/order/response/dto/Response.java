package com.caspar.order.response.dto;

import lombok.Builder;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-16
 */
@Builder
public class Response {

    private Object data;
    private Integer code;
    private String msg;
    private boolean success;

}
