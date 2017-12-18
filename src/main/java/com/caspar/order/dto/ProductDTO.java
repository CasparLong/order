package com.caspar.order.dto;

import com.caspar.order.entity.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-16
 */
@Data
public class ProductDTO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoDTO> productInfoDTOList;

    public ProductCategory convertToProductCategory() {
        ProductDTOConverter productDTOConverter = new ProductDTOConverter();
        return productDTOConverter.convert(this);
    }

    public static ProductDTO convertFor(ProductCategory productCategory) {
        ProductDTOConverter productDTOConverter = new ProductDTOConverter();
        return productDTOConverter.reverse().convert(productCategory);
    }

    private static class ProductDTOConverter extends Converter<ProductDTO, ProductCategory> {
        @Override
        protected ProductCategory doForward(ProductDTO productDTO) {
            ProductCategory productCategory = new ProductCategory();
            BeanUtils.copyProperties(productDTO, productCategory);
            return productCategory;
        }

        @Override
        protected ProductDTO doBackward(ProductCategory productCategory) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productCategory, productDTO);
            return productDTO;
        }
    }

}
