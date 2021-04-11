package com.digitalraider.maganin.web.rest;

import static javax.ws.rs.core.UriBuilder.fromPath;

import com.digitalraider.maganin.domain.ConsultancyType;
import com.digitalraider.maganin.service.ConsultancyTypeService;
import com.digitalraider.maganin.web.rest.errors.BadRequestAlertException;
import com.digitalraider.maganin.web.util.HeaderUtil;
import com.digitalraider.maganin.web.util.ResponseUtil;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.digitalraider.maganin.domain.ConsultancyType}.
 */
@Path("/api/consultancy-types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ConsultancyTypeResource {

    private final Logger log = LoggerFactory.getLogger(ConsultancyTypeResource.class);

    private static final String ENTITY_NAME = "consultancyType";

    @ConfigProperty(name = "application.name")
    String applicationName;


    @Inject
    ConsultancyTypeService consultancyTypeService;
    /**
     * {@code POST  /consultancy-types} : Create a new consultancyType.
     *
     * @param consultancyType the consultancyType to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new consultancyType, or with status {@code 400 (Bad Request)} if the consultancyType has already an ID.
     */
    @POST
    public Response createConsultancyType(ConsultancyType consultancyType, @Context UriInfo uriInfo) {
        log.debug("REST request to save ConsultancyType : {}", consultancyType);
        if (consultancyType.id != null) {
            throw new BadRequestAlertException("A new consultancyType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        var result = consultancyTypeService.persistOrUpdate(consultancyType);
        var response = Response.created(fromPath(uriInfo.getPath()).path(result.id.toString()).build()).entity(result);
        HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code PUT  /consultancy-types} : Updates an existing consultancyType.
     *
     * @param consultancyType the consultancyType to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated consultancyType,
     * or with status {@code 400 (Bad Request)} if the consultancyType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the consultancyType couldn't be updated.
     */
    @PUT
    public Response updateConsultancyType(ConsultancyType consultancyType) {
        log.debug("REST request to update ConsultancyType : {}", consultancyType);
        if (consultancyType.id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        var result = consultancyTypeService.persistOrUpdate(consultancyType);
        var response = Response.ok().entity(result);
        HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, consultancyType.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code DELETE  /consultancy-types/:id} : delete the "id" consultancyType.
     *
     * @param id the id of the consultancyType to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteConsultancyType(@PathParam("id") Long id) {
        log.debug("REST request to delete ConsultancyType : {}", id);
        consultancyTypeService.delete(id);
        var response = Response.noContent();
        HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code GET  /consultancy-types} : get all the consultancyTypes.
     *     * @return the {@link Response} with status {@code 200 (OK)} and the list of consultancyTypes in body.
     */
    @GET
    public List<ConsultancyType> getAllConsultancyTypes() {
        log.debug("REST request to get all ConsultancyTypes");
        return consultancyTypeService.findAll();
    }


    /**
     * {@code GET  /consultancy-types/:id} : get the "id" consultancyType.
     *
     * @param id the id of the consultancyType to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the consultancyType, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("/{id}")

    public Response getConsultancyType(@PathParam("id") Long id) {
        log.debug("REST request to get ConsultancyType : {}", id);
        Optional<ConsultancyType> consultancyType = consultancyTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consultancyType);
    }
}
