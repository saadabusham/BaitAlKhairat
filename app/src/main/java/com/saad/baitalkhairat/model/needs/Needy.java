package com.saad.baitalkhairat.model.needs;

import java.io.Serializable;

public class Needy implements Serializable {

    int id;

    String image;

    String title;

    String body;

    public Needy(int id, String image, String title, String body) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
