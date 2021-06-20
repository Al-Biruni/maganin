package com.digitalraider.maganin.web.rest;

import com.digitalraider.maganin.domain.Article;
import com.digitalraider.maganin.domain.Creation;
import com.digitalraider.maganin.security.AuthoritiesConstants;
import com.digitalraider.maganin.service.ArticleService;
import com.digitalraider.maganin.service.CreationService;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.service.dto.ArticleDTO;
import com.digitalraider.maganin.service.dto.ArticleSummery;
import com.digitalraider.maganin.web.rest.errors.BadRequestAlertException;
import com.digitalraider.maganin.web.rest.vm.PageRequestVM;
import com.digitalraider.maganin.web.rest.vm.SortRequestVM;
import com.digitalraider.maganin.web.util.HeaderUtil;
import com.digitalraider.maganin.web.util.PaginationUtil;
import com.digitalraider.maganin.web.util.ResponseUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static javax.ws.rs.core.UriBuilder.fromPath;
@Path("/api/creations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CreationResource {
    private final Logger log = LoggerFactory.getLogger(ArticleResource.class);

    private static final String ENTITY_NAME = "creation";

    @ConfigProperty(name = "application.name")
    String applicationName;


    @Inject
    CreationService articleService;
    /**
     * {@code POST  /articles} : Create a new article.
     *
     * @param article the article to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new article, or with status {@code 400 (Bad Request)} if the article has already an ID.
     */
    @POST
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Response createArticle(Creation article, @Context UriInfo uriInfo) {
        log.debug("REST request to save Article : {}", article);
        if (article.id != null) {
            throw new BadRequestAlertException("A new article cannot already have an ID", ENTITY_NAME, "idexists");
        }
        var result = articleService.persistOrUpdate(article);
        var response = Response.created(fromPath(uriInfo.getPath()).path(result.id.toString()).build()).entity(result);
        HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code PUT  /articles} : Updates an existing article.
     *
     * @param article the article to update.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the updated article,
     * or with status {@code 400 (Bad Request)} if the article is not valid,
     * or with status {@code 500 (Internal Server Error)} if the article couldn't be updated.
     */
    @PUT
    public Response updateArticle(Creation article) {
        log.debug("REST request to update Article : {}", article);
        if (article.id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        var result = articleService.persistOrUpdate(article);
        var response = Response.ok().entity(result);
        HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, article.id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code DELETE  /articles/:id} : delete the "id" article.
     *
     * @param id the id of the article to delete.
     * @return the {@link Response} with status {@code 204 (NO_CONTENT)}.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") Long id) {
        log.debug("REST request to delete Article : {}", id);
        articleService.delete(id);
        var response = Response.noContent();
        HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()).forEach(response::header);
        return response.build();
    }

    /**
     * {@code GET  /articles} : get all the articles.
     *
     * @param pageRequest the pagination information.
     * @return the {@link Response} with status {@code 200 (OK)} and the list of articles in body.
     */
    @GET
    public Response getAllArticles(@BeanParam PageRequestVM pageRequest, @BeanParam SortRequestVM sortRequest, @Context UriInfo uriInfo) {
        log.debug("REST request to get a page of Articles");
        var page = pageRequest.toPage();
        var sort = sortRequest.toSort();
        Paged<Creation> result = articleService.findAll(page,sort);
        var response = Response.ok().entity(result);
        response = PaginationUtil.withPaginationInfo(response, uriInfo, result);
        return response.build();
    }


    /**
     * {@code GET  /articles/:id} : get the "id" article.
     *
     * @param id the id of the article to retrieve.
     * @return the {@link Response} with status {@code 200 (OK)} and with body the article, or with status {@code 404 (Not Found)}.
     */
    @GET
    @Path("/{id}")

    public Response getArticle(@PathParam("id") Long id) {
        log.debug("REST request to get Article : {}", id);
        Optional<Creation> article = articleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(article);
    }
}
