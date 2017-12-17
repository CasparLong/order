package com.caspar.order.service.impl;

import com.caspar.order.entity.ProductCategory;
import com.caspar.order.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void save() {
        ProductCategory productCategory = productCategoryService.selectByCategoryId(2);

        log.info(productCategory.toString());

        productCategory.setCategoryName("热搜");

        productCategoryService.updateAll(productCategory);

    }

}