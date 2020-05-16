package com.saad.baitalkhairat.enums;

public enum DrawerWithIconTypes {
    NO_ICON(1),
    WITH_ICON(2),
    HIDE_ITEM(3),
    FULL_INVISIBLE_ITEM(4);

    private int typeID;

    DrawerWithIconTypes(int i) {
        this.typeID = i;
    }

    public static DrawerWithIconTypes fromInt(int i) {
        for (DrawerWithIconTypes type : DrawerWithIconTypes.values()) {
            if (type.getMode() == i) {
                return type;
            }
        }
        return null;
    }

    public int getMode() {
        return typeID;
    }
}
