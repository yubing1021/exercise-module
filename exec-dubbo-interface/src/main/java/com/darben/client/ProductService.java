package com.darben.client;

import com.darben.dto.Product;

public interface ProductService {

    /**
    *@Description: 查询商品
    *@Param: id
    *@return: Product
    *@date: 2020/5/10
    */
    Product getProduct(String id);
}
