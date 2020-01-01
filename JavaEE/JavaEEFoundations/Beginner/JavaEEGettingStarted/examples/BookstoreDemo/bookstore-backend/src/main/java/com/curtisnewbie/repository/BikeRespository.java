package com.curtisnewbie.repository;

import com.curtisnewbie.model.Bike;

import javax.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
public class BikeRespository {

    public Bike getBike() {
        Bike bike = new Bike("Curtis's Bike", "123-234-2");
        return bike;
    }
}
