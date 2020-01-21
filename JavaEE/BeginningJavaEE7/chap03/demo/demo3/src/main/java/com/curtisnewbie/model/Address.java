package com.curtisnewbie.model;

import com.curtisnewbie.constraints.PostCode;

import javax.validation.constraints.NotNull;

public class Address {

    @NotNull
    private String street1;
    private String street2;

    @NotNull
    private String city;
    private String state;

    @NotNull
    @PostCode
    private String postcode;
    private String country;
}
