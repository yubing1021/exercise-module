package com.annotation;

public enum TypeEnum {

    BUSINESS_API(1, "商家接口"),
    NOT_STORE_API(2, "非商城接口标识"),
    WHITE_API(3, "白名单"),
    INTERNAL_API(4, "内部系统调用api");

    TypeEnum(int type, String describe) {
        this.type = type;
        this.descript = describe;
    }

    private int type;
    private String descript;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
