package com.knight.myapplication.mData;

/**
 * Created by knight on 6/18/2017.
 */

public class MallList {

    int image;
    String title;
    String distance;

    public MallList(int image, String title, String distance) {
        this.image = image;
        this.title = title;
        this.distance = distance;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDistance() {
        return distance;
    }
}
