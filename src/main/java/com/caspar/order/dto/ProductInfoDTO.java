package com.caspar.order.dto;

import com.caspar.order.entity.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017-12-16
 */
@Data
public class ProductInfoDTO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

    public ProductInfo convertToProductInfo() {
        ProductInfoDTO.ProductInfoDTOConverter productInfoDTOConverter = new ProductInfoDTO.ProductInfoDTOConverter();
        return productInfoDTOConverter.convert(this);
    }

    public static ProductInfoDTO convertFor(ProductInfo productInfo) {
        ProductInfoDTO.ProductInfoDTOConverter productInfoDTOConverter = new ProductInfoDTO.ProductInfoDTOConverter();
        return productInfoDTOConverter.reverse().convert(productInfo);
    }

    private static class ProductInfoDTOConverter extends Converter<ProductInfoDTO, ProductInfo> {
        @Override
        protected ProductInfo doForward(ProductInfoDTO productInfoDTO) {
            ProductInfo productInfo = new ProductInfo();
            BeanUtils.copyProperties(productInfoDTO, productInfo);
            return productInfo;
        }

        @Override
        protected ProductInfoDTO doBackward(ProductInfo productInfo) {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(productInfo, productInfoDTO);
            return productInfoDTO;
        }
    }

}
