package com.darben.service.impl;

import com.darben.model.ProductInfo;
import com.darben.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-25 17:13
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductInfo getProduct(Long id) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(id.toString());
        productInfo.setName("A21 Polo衫");
        productInfo.setDesc("以纯线上品牌A21夏季新款2020男装，刺绣POLO衫翻领短袖");
        productInfo.setPrice(new BigDecimal(79.00));
        return productInfo;
    }
}
