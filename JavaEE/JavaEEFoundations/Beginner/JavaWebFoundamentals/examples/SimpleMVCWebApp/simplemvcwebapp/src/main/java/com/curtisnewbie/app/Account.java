/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curtisnewbie.app;

/**
 *
 * @author yongjie
 */
public class Account {

    private String name;
    // not safe, I know....
    private double deposit;

    public Account() {
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setDeposit(double d) {
        this.deposit = d;
    }

    public double getDeposit() {
        return deposit;
    }

}
