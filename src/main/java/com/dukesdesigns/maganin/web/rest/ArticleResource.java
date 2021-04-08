package com.dukesdesigns.maganin.web.rest;

import com.dukesdesigns.maganin.service.ArticleService;
import com.dukesdesigns.maganin.service.dto.Article;
import com.dukesdesigns.maganin.service.dto.ArticleSummery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArticleResource {
    @Inject
    ArticleService articleService;

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    @GET
    public Response getArticles(@QueryParam("pageNum") int pageNum , @QueryParam("sort") String sortBy){
        log.info("getting page" + pageNum + " with sort " + sortBy);

        List<ArticleSummery> articles = articleService.getArticlesSummeryByPage(pageNum,sortBy);
        return Response.status(Response.Status.OK).entity(articles).build();
    }

    @GET
    @Path("/{id}")
    public Response getArticleById(@PathParam("id") Long id){
        Article article = articleService.getArticleById(id);
        return Response.status(Response.Status.OK).entity(article).build();
    }

    @GET
    @Path("/summery")
    public Response getQueryArticlesSummery(
        @QueryParam("authorName") String authorName,
        @QueryParam("categoryName") String categoryName){
        List<ArticleSummery> articles = new ArrayList<>();
        if(authorName != null) {
            articles = articleService.getArticlesSummeryByAuthor(authorName);
        }
        if(categoryName != null) {
            articles = articles.stream().filter( article -> article.articleCategory == categoryName).collect(Collectors.toList());

        }
        return Response.status(Response.Status.OK).entity(articles).build();
    }


}
