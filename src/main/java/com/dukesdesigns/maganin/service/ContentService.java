package com.dukesdesigns.maganin.service;

import com.dukesdesigns.maganin.domain.Content;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dukesdesigns.maganin.domain.Content}.
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
     * @return the list of entities.
     */
    public  List<Content> findAll();


    /**
     * Get the "id" content.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Content> findOne(Long id);



}
