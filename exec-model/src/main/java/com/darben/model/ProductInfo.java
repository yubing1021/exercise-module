package com.darben.model;

import java.math.BigDecimal;

/**
 * @description: 产品信息
 * @author: darben
 * @create: 2020-05-04 18:10
 */
public class ProductInfo extends BaseInfo{

    private String id;
    private String name;
    private String desc;
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
