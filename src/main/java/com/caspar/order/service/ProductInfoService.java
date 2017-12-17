package com.caspar.order.service;

import com.caspar.order.dto.CartDTO;
import com.caspar.order.entity.ProductInfo;
import com.caspar.order.service.base.BaseService;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
public interface ProductInfoService extends BaseService<ProductInfo> {

    /**
     * 根据id查询商品
     *
     * @param productId
     * @return
     */
    ProductInfo selectByProductId(String productId);

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    List<ProductInfo> selectUpAll();

    /**
     * 增加库存
     *
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

}
