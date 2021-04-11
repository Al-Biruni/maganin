package com.digitalraider.maganin.service;

import com.digitalraider.maganin.domain.Consultancy;
import io.quarkus.panache.common.Page;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.digitalraider.maganin.domain.Consultancy}.
 */
public interface ConsultancyService {

    /**
     * Save a consultancy.
     *
     * @param consultancy the entity to save.
     * @return the persisted entity.
     */
    Consultancy persistOrUpdate(Consultancy consultancy);

    /**
     * Delete the "id" consultancy.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the consultancies.
     * @param page the pagination information.
     * @return the list of entities.
     */
    public Paged<Consultancy> findAll(Page page);

    /**
     * Get the "id" consultancy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Consultancy> findOne(Long id);



}
