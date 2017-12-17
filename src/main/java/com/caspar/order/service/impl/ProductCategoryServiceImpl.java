package com.caspar.order.service.impl;

import com.caspar.order.entity.ProductCategory;
import com.caspar.order.mapper.ProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
@Service
@Slf4j
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory> implements com.caspar.order.service.ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategory selectByCategoryId(Integer categoryId) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);

        return super.oneSelect(productCategory);
    }

    @Override
    public List<ProductCategory> selectByCategoryTypeIn(List<Integer> categoryTypeList) {
        Example example = new Example(ProductCategory.class);
        example.createCriteria().orIn("categoryType", categoryTypeList);

        return super.selectByExample(example);
    }
}
