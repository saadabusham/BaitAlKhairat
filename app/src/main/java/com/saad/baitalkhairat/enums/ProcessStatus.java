package com.saad.baitalkhairat.enums;

public enum ProcessStatus {
    WAITING(1),
    IN_PROCESS(2),
    FINISHED(3);

    private int status;

    ProcessStatus(int i) {
        this.status = i;
    }

    public static ProcessStatus fromInt(int i) {
        for (ProcessStatus type : ProcessStatus.values()) {
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
