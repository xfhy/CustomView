package com.hencoder.hencoderpracticedraw1.model;

/**
 * Created by xfhy on 2018/7/28 9:33
 * Description : 圆饼图model
 */
public class CircleModel {

    public CircleModel(String name, int size,int color) {
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public String name;
    public int size;
    public int color;
    /**
     * 比例
     */
    public float angle;

}
