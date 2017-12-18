package com.caspar.order;

import com.caspar.order.entity.ProductCategory;
import com.caspar.order.entity.ProductInfo;
import com.caspar.order.dto.ProductDTO;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-16
 */
public class BeanTest {

    @Test
    public void test() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("321");
        productInfo.setProductPrice(new BigDecimal(123));
        productInfo.setProductIcon("123");


        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1);
        productCategory.setCategoryName("aab");

        ProductDTO productDTO = new ProductDTO().convertFor(productCategory);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setCategoryName("aab");
        ProductCategory p = productDTO1.convertToProductCategory();

        System.out.println(p);


    }

}
