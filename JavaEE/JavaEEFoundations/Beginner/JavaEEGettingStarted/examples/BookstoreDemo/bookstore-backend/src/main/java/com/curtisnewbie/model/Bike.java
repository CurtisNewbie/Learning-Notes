package com.curtisnewbie.model;

public class Bike {

    private String name;
    private String model;

    public Bike() {
    }

    public Bike(String n, String m) {
        this.name = n;
        this.model = m;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
