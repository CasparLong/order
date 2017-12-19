package com.caspar.order.response;

import com.caspar.order.enums.ResponseEnum;
import com.caspar.order.response.dto.Response;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-16
 */
public class ResponseBuilder {

    public ResponseBuilder() {
    }

    public static Response buildSuccess() {
        return build(true, ResponseEnum.SUCCESS.getCode(), (Object) null, ResponseEnum.SUCCESS.getMessage());
    }

    public static Response buildSuccess(Object data) {
        return build(true, ResponseEnum.SUCCESS.getCode(), data, ResponseEnum.SUCCESS.getMessage());
    }

    public static Response buildFail() {
        return build(false, ResponseEnum.FAIL.getCode(), (Object) null, ResponseEnum.FAIL.getMessage());
    }

    public static Response buildFail(String msg) {
        return build(false, ResponseEnum.FAIL.getCode(), (Object) null, msg);
    }

    public static Response buildFail(Integer code, String msg) {
        return build(true, code, (Object) null, msg);
    }

    public static Response build(boolean success, Integer code) {
        return build(success, code, (Object) null, (String) null);
    }

    public static Response build(boolean success, Integer code, Object data) {
        return build(success, code, data, (String) null);
    }

    public static Response build(boolean success, Integer code, Object data, String msg) {
        return Response.builder().success(success).code(code).data(data).msg(msg).build();
    }

}
