package com.saad.baitalkhairat.enums;

public enum OrderTypes {
    ACTIVE(1),
    HISTORY(2),
    UNRATED(3);

    private int typeID;

    OrderTypes(int i) {
        this.typeID = i;
    }

    public static OrderTypes fromInt(int i) {
        for (OrderTypes type : OrderTypes.values()) {
            if (type.getTypeValue() == i) {
                return type;
            }
        }
        return null;
    }

    public int getTypeValue() {
        return typeID;
    }
}
