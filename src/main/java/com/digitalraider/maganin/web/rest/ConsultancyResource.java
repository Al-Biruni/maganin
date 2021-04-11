package com.digitalraider.maganin.web.rest;

import static javax.ws.rs.core.UriBuilder.fromPath;

import com.digitalraider.maganin.domain.Consultancy;
import com.digitalraider.maganin.service.ConsultancyService;
import com.digitalraider.maganin.web.rest.errors.BadRequestAlertException;
import com.digitalraider.maganin.web.util.HeaderUtil;
import com.digitalraider.maganin.web.util.ResponseUtil;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.web.rest.vm.PageRequestVM;
import com.digitalraider.maganin.web.rest.vm.SortRequestVM;
import com.digitalraider.maganin.web.util.PaginationUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.digitalraider.maganin.domain.Consultancy}.
 */
@Path("/api/consultancies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ConsultancyResource {

    private final Logger log = LoggerFactory.getLogger(ConsultancyResource.class);

    private static final String ENTITY_NAME = "consultancy";

    @ConfigProperty(name = "application.name")
    String applicationName;


    @Inject
    ConsultancyService consultancyService;
    /**
     * {@code POST  /consultancies} : Create a new consultancy.
     *
     * @param consultancy the consultancy to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new consultancy, or with status {@code 400 (Bad Request)} if the consultancy has already an ID.
     */
    @POST
    public Response createConsultancy(Consultancy consultancy, @Context UriInfo uriInfo) {
        log.debug("REST request to save Consultancy : {}", consultancy);
        if (consultancy.id != null) {
            throw new BadRequestAlertException("A new consultancy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        var result = consultancyService.persistOrUpdate(consultancy);
        var response = Response.created(fromPath(uriInfo.getPath()).path(result.id.toString()).build()).entity(result);
        HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code PUT  /consultancies} : Updates an existing consultancy.
     *
     * @param consultancy the consultancy to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated consultancy,
     * or with status {@code 400 (Bad Request)} if the consultancy is not valid,
     * or with status {@code 500 (Internal Server Error)} if the consultancy couldn't be updated.
     */
    @PUT
    public Response updateConsultancy(Consultancy consultancy) {
        log.debug("REST request to update Consultancy : {}", consultancy);
        if (consultancy.id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        var result = consultancyService.persistOrUpdate(consultancy);
        var response = Response.ok().entity(result);
        HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, consultancy.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code DELETE  /consultancies/:id} : delete the "id" consultancy.
     *
     * @param id the id of the consultancy to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteConsultancy(@PathParam("id") Long id) {
        log.debug("REST request to delete Consultancy : {}", id);
        consultancyService.delete(id);
        var response = Response.noContent();
        HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code GET  /consultancies} : get all the consultancies.
     *
     * @param pageRequest the pagination information.
     * @return the {@link Response} with status {@code 200 (OK)} and the list of consultancies in body.
     */
    @GET
    public Response getAllConsultancies(@BeanParam PageRequestVM pageRequest, @BeanParam SortRequestVM sortRequest, @Context UriInfo uriInfo) {
        log.debug("REST request to get a page of Consultancies");
        var page = pageRequest.toPage();
        var sort = sortRequest.toSort();
        Paged<Consultancy> result = consultancyService.findAll(page);
        var response = Response.ok().entity(result.content);
        response = PaginationUtil.withPaginationInfo(response, uriInfo, result);
        return response.build();
    }


    /**
     * {@code GET  /consultancies/:id} : get the "id" consultancy.
     *
     * @param id the id of the consultancy to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the consultancy, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("/{id}")

    public Response getConsultancy(@PathParam("id") Long id) {
        log.debug("REST request to get Consultancy : {}", id);
        Optional<Consultancy> consultancy = consultancyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consultancy);
    }
}
