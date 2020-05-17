package com.saad.baitalkhairat.model;

import java.io.Serializable;

public class MyNeeds implements Serializable {

    int process;

    public MyNeeds(int process) {
        this.process = process;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }
}
