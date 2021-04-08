package com.dukesdesigns.maganin.web.rest;

import static javax.ws.rs.core.UriBuilder.fromPath;

import com.dukesdesigns.maganin.domain.Category;
import com.dukesdesigns.maganin.service.CategoryService;
import com.dukesdesigns.maganin.web.rest.errors.BadRequestAlertException;
import com.dukesdesigns.maganin.web.util.HeaderUtil;
import com.dukesdesigns.maganin.web.util.ResponseUtil;

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
 * REST controller for managing {@link com.dukesdesigns.maganin.domain.Category}.
 */
@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "category";

    @ConfigProperty(name = "application.name")
    String applicationName;


    @Inject
    CategoryService categoryService;
    /**
     * {@code POST  /categories} : Create a new category.
     *
     * @param category the category to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new category, or with status {@code 400 (Bad Request)} if the category has already an ID.
     */
    @POST
    public Response createCategory(Category category, @Context UriInfo uriInfo) {
        log.debug("REST request to save Category : {}", category);
        if (category.id != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        var result = categoryService.persistOrUpdate(category);
        var response = Response.created(fromPath(uriInfo.getPath()).path(result.id.toString()).build()).entity(result);
        HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code PUT  /categories} : Updates an existing category.
     *
     * @param category the category to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated category,
     * or with status {@code 400 (Bad Request)} if the category is not valid,
     * or with status {@code 500 (Internal Server Error)} if the category couldn't be updated.
     */
    @PUT
    public Response updateCategory(Category category) {
        log.debug("REST request to update Category : {}", category);
        if (category.id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        var result = categoryService.persistOrUpdate(category);
        var response = Response.ok().entity(result);
        //HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, category.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code DELETE  /categories/:id} : delete the "id" category.
     *
     * @param id the id of the category to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        var response = Response.noContent();
        HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code GET  /categories} : get all the categories.
     *     * @return the {@link Response} with status {@code 200 (OK)} and the list of categories in body.
     */
    @GET
    public List<Category> getAllCategories() {
        log.debug("REST request to get all Categories");
        return categoryService.findAll();
    }


    /**
     * {@code GET  /categories/:id} : get the "id" category.
     *
     * @param id the id of the category to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the category, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("/{id}")

    public Response getCategory(@PathParam("id") Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<Category> category = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(category);
    }
}
