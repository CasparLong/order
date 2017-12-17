package com.caspar.order.util;

import com.caspar.order.enums.CodeEnum;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-17
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}
