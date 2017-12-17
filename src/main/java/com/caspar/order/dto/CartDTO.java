package com.caspar.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:购物车
 *
 * @author Caspar
 * @Date 2017/12/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

}
