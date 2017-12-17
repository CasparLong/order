package com.caspar.order.controller;

import com.caspar.order.entity.ProductCategory;
import com.caspar.order.entity.ProductInfo;
import com.caspar.order.response.ResponseBuilder;
import com.caspar.order.response.dto.ProductDTO;
import com.caspar.order.response.dto.ProductInfoDTO;
import com.caspar.order.response.dto.Response;
import com.caspar.order.service.ProductCategoryService;
import com.caspar.order.service.ProductInfoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:买家商品相关
 *
 * @author Caspar
 * @Date 2017-12-16
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    @RequestMapping("/list")
    public Response productList() {
        // 查询所有上架商品
        List<ProductInfo> productInfos = productInfoService.selectUpAll();

        // 查询类目
        List<Integer> categoryTypeList = productInfos.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryService.selectByCategoryTypeIn(categoryTypeList);

        // 组装数据
        List<ProductDTO> productDTOList = Lists.newArrayList();
        for (ProductCategory productCategory : productCategories) {
            ProductDTO productDTO = new ProductDTO().convertFor(productCategory);

            List<ProductInfoDTO> productInfoDTOList = Lists.newArrayList();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoDTO productInfoDTO = new ProductInfoDTO().convertFor(productInfo);
                    productInfoDTOList.add(productInfoDTO);
                }
            }

            productDTO.setProductInfoDTOList(productInfoDTOList);
            productDTOList.add(productDTO);
        }

        return ResponseBuilder.buildSuccess(productDTOList);
    }

}
