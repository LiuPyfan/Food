package com.pf.dllo.food.bean;

/**
 * Created by dllo on 16/11/23.
 */
public class MyBean {
    private String name;
    private int right;
    private int left;

    public MyBean(String name, int right, int left) {
        this.name = name;
        this.right = right;
        this.left = left;
    }

    public MyBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}
