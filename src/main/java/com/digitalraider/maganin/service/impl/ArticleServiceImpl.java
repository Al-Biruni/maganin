package com.digitalraider.maganin.service.impl;

import com.digitalraider.maganin.service.ArticleService;
import io.quarkus.panache.common.Page;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.domain.Article;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);



    @Override
    @Transactional
    public Article persistOrUpdate(Article article) {
        log.debug("Request to save Article : {}", article);
        return Article.persistOrUpdate(article);
    }

    /**
     * Delete the Article by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Article : {}", id);
        Article.findByIdOptional(id).ifPresent(article -> {
            article.delete();
        });
    }

    /**
     * Get one article by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Article> findOne(Long id) {
        log.debug("Request to get Article : {}", id);
        return Article.findByIdOptional(id);
    }

    /**
     * Get all the articles.
     * @param page the pagination information.
     * @return the list of entities.
     */
    @Override
    public Paged<Article> findAll(Page page, Sort sort) {
        log.debug("Request to get all Articles");
        return new Paged<>(Article.findAll(sort).page(page));    }



}
