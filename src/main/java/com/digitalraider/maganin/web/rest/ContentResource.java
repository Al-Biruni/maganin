package com.digitalraider.maganin.web.rest;

import static javax.ws.rs.core.UriBuilder.fromPath;

import com.digitalraider.maganin.domain.Content;
import com.digitalraider.maganin.service.ContentService;
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
 * REST controller for managing {@link com.digitalraider.maganin.domain.Content}.
 */
@Path("/api/contents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ContentResource {

    private final Logger log = LoggerFactory.getLogger(ContentResource.class);

    private static final String ENTITY_NAME = "content";

    @ConfigProperty(name = "application.name")
    String applicationName;


    @Inject
    ContentService contentService;
    /**
     * {@code POST  /contents} : Create a new content.
     *
     * @param content the content to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new content, or with status {@code 400 (Bad Request)} if the content has already an ID.
     */
    @POST
    public Response createContent(Content content, @Context UriInfo uriInfo) {
        log.debug("REST request to save Content : {}", content);
        if (content.id != null) {
            throw new BadRequestAlertException("A new content cannot already have an ID", ENTITY_NAME, "idexists");
        }
        var result = contentService.persistOrUpdate(content);
        var response = Response.created(fromPath(uriInfo.getPath()).path(result.id.toString()).build()).entity(result);
        HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code PUT  /contents} : Updates an existing content.
     *
     * @param content the content to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated content,
     * or with status {@code 400 (Bad Request)} if the content is not valid,
     * or with status {@code 500 (Internal Server Error)} if the content couldn't be updated.
     */
    @PUT
    public Response updateContent(Content content) {
        log.debug("REST request to update Content : {}", content);
        if (content.id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        var result = contentService.persistOrUpdate(content);
        var response = Response.ok().entity(result);
        HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, content.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code DELETE  /contents/:id} : delete the "id" content.
     *
     * @param id the id of the content to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteContent(@PathParam("id") Long id) {
        log.debug("REST request to delete Content : {}", id);
        contentService.delete(id);
        var response = Response.noContent();
        HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code GET  /contents} : get all the contents.
     *
     * @param pageRequest the pagination information.
     * @return the {@link Response} with status {@code 200 (OK)} and the list of contents in body.
     */
    @GET
    public Response getAllContents(@BeanParam PageRequestVM pageRequest, @BeanParam SortRequestVM sortRequest, @Context UriInfo uriInfo) {
        log.debug("REST request to get a page of Contents");
        var page = pageRequest.toPage();
        var sort = sortRequest.toSort();
        Paged<Content> result = contentService.findAll(page);
        var response = Response.ok().entity(result.content);
        response = PaginationUtil.withPaginationInfo(response, uriInfo, result);
        return response.build();
    }


    /**
     * {@code GET  /contents/:id} : get the "id" content.
     *
     * @param id the id of the content to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the content, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("/{id}")

    public Response getContent(@PathParam("id") Long id) {
        log.debug("REST request to get Content : {}", id);
        Optional<Content> content = contentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(content);
    }
}
