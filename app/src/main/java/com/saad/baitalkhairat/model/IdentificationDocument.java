package com.saad.baitalkhairat.model;

import java.io.Serializable;

public class IdentificationDocument implements Serializable {

    String image;

    public IdentificationDocument(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
