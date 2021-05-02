package com.digitalraider.maganin.service;

import com.digitalraider.maganin.domain.Article;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.digitalraider.maganin.domain.Article}.
 */
public interface ArticleService {

    /**
     * Save a article.
     *
     * @param article the entity to save.
     * @return the persisted entity.
     */
    Article persistOrUpdate(Article article);

    /**
     * Delete the "id" article.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the articles.
     * @param page the pagination information.
     * @return the list of entities.
     */
    public Paged<Article> findAll(Page page, Sort sort);

    /**
     * Get the "id" article.
     *
     * @param id the id of the entity.
     * @return the entity.
     */

    Optional<Article> findOne(Long id);


}
