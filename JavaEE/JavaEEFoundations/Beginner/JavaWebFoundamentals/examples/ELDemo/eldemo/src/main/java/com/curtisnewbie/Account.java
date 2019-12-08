package com.curtisnewbie;

public class Account {

    private String name;
    private double money;
    private int age;
    private String[] randomStrings;

    public Account() {

    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getRandomStrings() {
        return this.randomStrings;
    }

    public void setRandomStrings(String[] strs) {
        this.randomStrings = strs;
    }
}
