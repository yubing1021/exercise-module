package com.darben.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: darben
 * @create: 2020-05-10 21:36
 */
@Data
public class Product implements Serializable {
    private String id;
    private String productName;
    private String desc;
}
