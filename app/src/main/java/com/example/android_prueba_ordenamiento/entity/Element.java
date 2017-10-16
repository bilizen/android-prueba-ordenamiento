package com.example.android_prueba_ordenamiento.entity;

/**
 * Created by Bill on 15/10/2017.
 */

public class Element {
    private int id;
    private String name;
    private int orderCurrent;
    private int orderLast;
    private int cont;
    private String color;

    public Element() {
    }

    public Element(String name, int orderCurrent, int orderLast, int cont, String color) {
        this.name = name;
        this.orderCurrent = orderCurrent;
        this.orderLast = orderLast;
        this.cont = cont;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderCurrent() {
        return orderCurrent;
    }

    public void setOrderCurrent(int orderCurrent) {
        this.orderCurrent = orderCurrent;
    }

    public int getOrderLast() {
        return orderLast;
    }

    public void setOrderLast(int orderLast) {
        this.orderLast = orderLast;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
