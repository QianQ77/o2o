package com.qian.o2o.enums;

/**
 * Created by qiuqian on 9/21/19.
 */
public enum ShopStateEnum {
    CHECK(0, "Under Review"), OFFLINE(-1, "Illegal"), SUCCESS(1, "Success"), PASS(2, "Approved"), INNER_ERROR(-1001,
            "System Error"), NULL_SHOPID(-1002, "ShopId is Null"), NULL_SHOP(-1003, "shop information is Null");
    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * return corresponding ShopStateEnum
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

}

