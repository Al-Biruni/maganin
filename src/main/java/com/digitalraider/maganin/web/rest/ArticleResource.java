package com.digitalraider.maganin.web.rest;

import static javax.ws.rs.core.UriBuilder.fromPath;

import com.digitalraider.maganin.domain.Article;
import com.digitalraider.maganin.security.AuthoritiesConstants;
import com.digitalraider.maganin.service.ArticleService;
import com.digitalraider.maganin.service.dto.ArticleDTO;
import com.digitalraider.maganin.service.dto.ArticleSummery;
import com.digitalraider.maganin.web.rest.errors.BadRequestAlertException;
import com.digitalraider.maganin.web.util.HeaderUtil;
import com.digitalraider.maganin.web.util.ResponseUtil;

import liquibase.pro.packaged.A;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.web.rest.vm.PageRequestVM;
import com.digitalraider.maganin.web.rest.vm.SortRequestVM;
import com.digitalraider.maganin.web.util.PaginationUtil;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link com.digitalraider.maganin.domain.Article}.
 */
@Path("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArticleResource {

    private final Logger log = LoggerFactory.getLogger(ArticleResource.class);

    private static final String ENTITY_NAME = "article";

    @ConfigProperty(name = "application.name")
    String applicationName;


    @Inject
    ArticleService articleService;
    /**
     * {@code POST  /articles} : Create a new article.
     *
     * @param article the article to create.
     * @return the {@link Response} with status {@code 201 (Created)} and with body the new article, or with status {@code 400 (Bad Request)} if the article has already an ID.
     */
    @POST
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Response createArticle(Article article, @Context UriInfo uriInfo) {
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
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Response updateArticle(Article article) {
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
    @RolesAllowed(AuthoritiesConstants.ADMIN)
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
        Paged<Article> result = articleService.findAll(page,sort);
        List<ArticleSummery> resultSummery = result.content.stream().
            map(article -> new ArticleSummery(article)).
            collect(Collectors.toList());

        var response = Response.ok().entity(resultSummery);
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
        Optional<ArticleDTO> article = articleService.findOne(id).map(article1 -> new ArticleDTO(article1));
        return ResponseUtil.wrapOrNotFound(article);
    }


}
