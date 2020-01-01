package com.curtisnewbie.rest;

import com.curtisnewbie.model.Bike;
import com.curtisnewbie.repository.BikeRespository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bike")
public class BikeEndPoint {

    @Inject
    private BikeRespository bikeRespository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Bike getOneBike() {
        return bikeRespository.getBike();
    }
}
