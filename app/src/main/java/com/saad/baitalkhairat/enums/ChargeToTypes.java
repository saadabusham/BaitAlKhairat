package com.saad.baitalkhairat.enums;

public enum ChargeToTypes {
    MY_WALLET(1),
    BAIT_AL_KHAIRAT_ACCOUNT(2);

    private int typeID;

    ChargeToTypes(int i) {
        this.typeID = i;
    }

    public static ChargeToTypes fromInt(int i) {
        for (ChargeToTypes type : ChargeToTypes.values()) {
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
