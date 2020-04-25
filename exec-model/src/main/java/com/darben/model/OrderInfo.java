package com.darben.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @description: 订单信息Model
 * @author: darben
 * @create: 2020-04-05 00:55
 */
@Getter
@Setter
@ToString
public class OrderInfo {

    private String id;
    private String userid;
    private String goodsName;
    private String price;
    private String payType;
    private String receiveAddress;
    private String creatBy;
    private Date createTime;
    private String updateBy;
    private String updateTime;
}
