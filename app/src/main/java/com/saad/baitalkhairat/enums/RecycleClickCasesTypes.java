package com.saad.baitalkhairat.enums;

public enum RecycleClickCasesTypes {
    DETAILS(1),
    ADD_TO_CART(2),
    DONATE(3);

    private int typeID;

    RecycleClickCasesTypes(int i) {
        this.typeID = i;
    }

    public static RecycleClickCasesTypes fromInt(int i) {
        for (RecycleClickCasesTypes type : RecycleClickCasesTypes.values()) {
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
