package com.combat.subscribe;

/**
 * @description:
 * @author: darben
 * @create: 2020-03-27 14:46
 */
public class ServerData {

    //ip地址
    private String address;
    //id
    private Integer id;
    //服务名称
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
