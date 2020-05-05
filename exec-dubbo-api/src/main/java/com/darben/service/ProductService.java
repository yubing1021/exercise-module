package com.darben.service;

import com.darben.model.ProductInfo;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-25 17:10
 */
public interface ProductService {

    ProductInfo getProduct(Long id);
}
