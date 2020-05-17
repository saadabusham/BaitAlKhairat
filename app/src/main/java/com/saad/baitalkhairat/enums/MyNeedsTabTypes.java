package com.saad.baitalkhairat.enums;

public enum MyNeedsTabTypes {
    CURRENT(1),
    HISTORY(2);

    private int typeID;

    MyNeedsTabTypes(int i) {
        this.typeID = i;
    }

    public static MyNeedsTabTypes fromInt(int i) {
        for (MyNeedsTabTypes type : MyNeedsTabTypes.values()) {
            if (type.getType() == i) {
                return type;
            }
        }
        return null;
    }

    public int getType() {
        return typeID;
    }
}
