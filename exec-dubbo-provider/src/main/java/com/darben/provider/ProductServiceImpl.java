package com.darben.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.darben.client.ProductService;
import com.darben.dto.Product;

/**
 * @description:
 * @author: darben
 * @create: 2020-05-10 21:39
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProduct(String id) {
        Product product = new Product();
        product.setId(id);
        product.setProductName("测试商品");
        product.setDesc("测试商品的描述信息");
        return product;
    }
}
