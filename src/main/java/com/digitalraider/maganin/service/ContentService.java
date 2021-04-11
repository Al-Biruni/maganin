package com.digitalraider.maganin.service;

import com.digitalraider.maganin.domain.Content;
import io.quarkus.panache.common.Page;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.digitalraider.maganin.domain.Content}.
 */
public interface ContentService {

    /**
     * Save a content.
     *
     * @param content the entity to save.
     * @return the persisted entity.
     */
    Content persistOrUpdate(Content content);

    /**
     * Delete the "id" content.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the contents.
     * @param page the pagination information.
     * @return the list of entities.
     */
    public Paged<Content> findAll(Page page);

    /**
     * Get the "id" content.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Content> findOne(Long id);



}
