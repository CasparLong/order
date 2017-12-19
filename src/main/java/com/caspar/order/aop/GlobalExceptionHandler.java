package com.caspar.order.aop;

import com.alibaba.fastjson.JSONException;
import com.caspar.order.enums.ResponseEnum;
import com.caspar.order.exception.SellException;
import com.caspar.order.response.ResponseBuilder;
import com.caspar.order.response.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:全局异常处理器
 *
 * @author Caspar
 * @Date 2017/12/19
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * JSONException,JSON转换相关异常
     */
    @ExceptionHandler({JSONException.class})
    @ResponseBody
    public Response jsonExceptionHandler(JSONException e) {
        log.error(e.getMessage(), e);
        return ResponseBuilder.buildFail(ResponseEnum.JSON_PARSE_ERROR.getCode(), ResponseEnum.JSON_PARSE_ERROR.getMessage());
    }

    /**
     * NullPointerException,空指针
     */
    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    public Response nullPointerExceptionHandler(NullPointerException e) {
        log.error(e.getMessage(), e);
        return ResponseBuilder.buildFail(ResponseEnum.SYSTEM_ERROR.getCode(), ResponseEnum.SYSTEM_ERROR.getMessage());
    }

    /**
     * ClassCastException,类型转换错误
     */
    @ExceptionHandler({ClassCastException.class})
    @ResponseBody
    public Response classCastExceptionHandler(ClassCastException e) {
        log.error(e.getMessage(), e);
        return ResponseBuilder.buildFail(ResponseEnum.ILLEGAL_ARGUMENT.getCode(), ResponseEnum.ILLEGAL_ARGUMENT.getMessage());
    }

    /**
     * ClassNotFoundException,找不到类
     */
    @ExceptionHandler({ClassNotFoundException.class})
    @ResponseBody
    public Response classNotFoundExceptionHandler(ClassNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseBuilder.buildFail(ResponseEnum.PROGRAM_ERROR.getCode(), ResponseEnum.PROGRAM_ERROR.getMessage());
    }

    /**
     * SellException,业务异常
     */
    @ExceptionHandler({SellException.class})
    @ResponseBody
    public Response sellExceptionHandler(SellException e) {
        log.error("业务异常,{}", e.getMessage(), e);
        return ResponseBuilder.buildFail(e.getCode(), e.getMessage());
    }

    /**
     * RuntimeException,运行时异常
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody

    public Response runtimeExceptionHandler(RuntimeException e) {
        log.error(e.getLocalizedMessage(), e);
        return ResponseBuilder.buildFail(ResponseEnum.PROGRAM_ERROR.getCode(), e.getMessage());
    }

    /**
     * Exception
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Response exceptionHandler(Exception e) {
        log.error(e.getLocalizedMessage(), e);
        return ResponseBuilder.buildFail(ResponseEnum.SYSTEM_ERROR.getCode(), ResponseEnum.SYSTEM_ERROR.getMessage());
    }

}
