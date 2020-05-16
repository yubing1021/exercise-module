package com.darben.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.darben.client.ProductService;
import com.darben.dto.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: darben
 * @create: 2020-05-10 21:49
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Reference
    private ProductService productService;

    @RequestMapping("/get")
    public Product getProduct(String id){
        Product product = productService.getProduct(id);
        return product;
    }

}
