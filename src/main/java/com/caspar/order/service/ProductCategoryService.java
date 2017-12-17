package com.caspar.order.service;

import com.caspar.order.entity.ProductCategory;
import com.caspar.order.service.base.BaseService;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
public interface ProductCategoryService extends BaseService<ProductCategory> {

    /**
     * 根据categoryId查询
     *
     * @param categoryId
     * @return
     */
    ProductCategory selectByCategoryId(Integer categoryId);

    /**
     * 根据类别id查询
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> selectByCategoryTypeIn(List<Integer> categoryTypeList);

}
