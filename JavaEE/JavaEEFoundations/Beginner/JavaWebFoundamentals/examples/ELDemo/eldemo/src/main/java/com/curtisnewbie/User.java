package com.curtisnewbie;

/** User Bean */
public class User {

    private String name;
    private String[] veggie;

    public User(){

    }

    public String getName(){
        return this.name;
    }

    public String[] getVeggie(){
        return this.veggie;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setVeggie(String[] veg){
        this.veggie = veg;
    }
}
