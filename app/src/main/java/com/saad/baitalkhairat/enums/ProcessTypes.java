package com.saad.baitalkhairat.enums;

public enum ProcessTypes {
    ORDER_RECEIVED(1),
    UNDER_STUDYING(2),
    IN_WAY(3),
    FINISHED(4);

    private int status;

    ProcessTypes(int i) {
        this.status = i;
    }

    public static ProcessTypes fromInt(int i) {
        for (ProcessTypes type : ProcessTypes.values()) {
            if (type.getStatus() == i) {
                return type;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }
}
