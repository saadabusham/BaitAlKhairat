package com.saad.baitalkhairat.model;

import java.io.Serializable;

public class Amount implements Serializable {

    String amount;
    String currency;

    boolean selected = false;

    public Amount(String amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getAmountFormatted() {
        return amount + " " + currency;
    }
}
