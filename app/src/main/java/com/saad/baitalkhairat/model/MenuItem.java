package com.saad.baitalkhairat.model;

public class MenuItem {
    public int title;
    public int image;
    public int isWithIcon;
    public int notificationCount;

    public MenuItem(int title, int image, int isWithIcon) {
        this.title = title;
        this.image = image;
        this.isWithIcon = isWithIcon;
    }


    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int isWithIcon() {
        return isWithIcon;
    }

    public void setWithIcon(int withIcon) {
        isWithIcon = withIcon;
    }

    public int getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }
}
