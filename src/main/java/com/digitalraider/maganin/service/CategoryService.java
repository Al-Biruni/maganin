package com.digitalraider.maganin.service;

import com.digitalraider.maganin.domain.Category;
import io.quarkus.panache.common.Page;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.digitalraider.maganin.domain.Category}.
 */
public interface CategoryService {

    /**
     * Save a category.
     *
     * @param category the entity to save.
     * @return the persisted entity.
     */
    Category persistOrUpdate(Category category);

    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the categories.
     * @param page the pagination information.
     * @return the list of entities.
     */
    public Paged<Category> findAll(Page page);

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Category> findOne(Long id);



}
