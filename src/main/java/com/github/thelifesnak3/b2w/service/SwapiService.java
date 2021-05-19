package com.github.thelifesnak3.b2w.service;

import com.github.thelifesnak3.b2w.dto.SwapiDTO;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/planets/")
@ApplicationScoped
@RegisterRestClient(configKey = "swapi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterProvider(SwapiResponseMapper.class)
public interface SwapiService {

    @GET
    SwapiDTO getPlanet(@QueryParam("search") String search);
}
