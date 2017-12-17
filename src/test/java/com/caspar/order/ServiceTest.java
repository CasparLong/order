package com.caspar.order;

import com.caspar.order.entity.ProductCategory;
import com.caspar.order.entity.ProductInfo;
import com.caspar.order.service.ProductCategoryService;
import com.caspar.order.service.ProductInfoService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void test() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("zzc");
        productInfo.setProductName("caspar");
        productInfo.setProductPrice(new BigDecimal(100));
        productInfo.setProductStock(1);
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("123");
        productInfo.setProductStatus(1);
        productInfo.setProductIcon("zzzz");

        productInfoService.save(productInfo);

    }

    @Test
    public void test1() {
        Example example = new Example(ProductCategory.class);
        List<Integer> types = Lists.newArrayList();
        types.add(1);
        types.add(2);
        example.createCriteria().orIn("categoryType", types);

        List<ProductCategory> productCategories = productCategoryService.selectByExample(example);

        System.out.println(productCategories);
    }



}
