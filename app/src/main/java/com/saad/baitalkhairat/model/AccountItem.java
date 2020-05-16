package com.saad.baitalkhairat.model;

import java.io.Serializable;

public class AccountItem implements Serializable {

    int text;
    int image;

    public AccountItem(int text, int image) {
        this.text = text;
        this.image = image;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
