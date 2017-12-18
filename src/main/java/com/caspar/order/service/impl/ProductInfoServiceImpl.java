package com.caspar.order.service.impl;

import com.caspar.order.dto.CartDTO;
import com.caspar.order.entity.ProductInfo;
import com.caspar.order.enums.ProductStatusEnum;
import com.caspar.order.enums.ResponseEnum;
import com.caspar.order.exception.SellException;
import com.caspar.order.mapper.ProductInfoMapper;
import com.caspar.order.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Caspar
 * @Date 2017/12/13
 */
@Service
@Slf4j
public class ProductInfoServiceImpl extends BaseServiceImpl<ProductInfo> implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public ProductInfo selectByProductId(String productId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);

        return super.oneSelect(productInfo);
    }

    @Override
    public List<ProductInfo> selectUpAll() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return super.select(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = this.selectByProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResponseEnum.PRODUCT_NOT_EXIST);
            }

            int result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            productInfoMapper.updateByPrimaryKeySelective(productInfo);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = this.selectByProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResponseEnum.PRODUCT_NOT_EXIST);
            }

            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResponseEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            productInfoMapper.updateByPrimaryKeySelective(productInfo);
        }

    }
}
