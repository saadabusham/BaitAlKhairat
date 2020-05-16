package com.saad.baitalkhairat.enums;

public enum SignTypes {
    LOGIN(0),
    REGISTER(1);

    private int typeID;

    SignTypes(int i) {
        this.typeID = i;
    }

    public static SignTypes fromInt(int i) {
        for (SignTypes type : SignTypes.values()) {
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
