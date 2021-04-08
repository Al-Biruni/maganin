package com.dukesdesigns.maganin.web.rest;

import com.dukesdesigns.maganin.service.ConsultancyService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/consultancy")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ConsultancyResource {
    @Inject
    ConsultancyService consultancyService;

    @GET
    public Response getConsultanciesByPage(){
        return Response.status(Response.Status.OK).entity(consultancyService.findOne(50L)).build();
    }
}
