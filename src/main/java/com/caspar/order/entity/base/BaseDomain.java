package com.caspar.order.entity.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
public abstract class BaseDomain implements Serializable {

    private static final long serialVersionUID = 7467101484872606877L;

    public <T> T convertExt(Class<T> clazz) {
        String json = JSON.toJSONString(this);
        T t = JSON.parseObject(json, clazz);
        return t;
    }

}
