package com.saad.baitalkhairat.enums;

public enum FillOrderResultsTypes {
    IMAGES(1),
    LOCATION(2),
    ORDER_EXTRAS(3),
    PICKUP_LOCATION(4),
    DROP_LOCATION(5);

    private int typeID;

    FillOrderResultsTypes(int i) {
        this.typeID = i;
    }

    public static FillOrderResultsTypes fromInt(int i) {
        for (FillOrderResultsTypes type : FillOrderResultsTypes.values()) {
            if (type.getValue() == i) {
                return type;
            }
        }
        return null;
    }

    public int getValue() {
        return typeID;
    }
}
