package com.dukesdesigns.maganin.service;

import com.dukesdesigns.maganin.domain.Consultancy;

import java.util.List;
import java.util.Optional;

public interface ConsultancyService {
    Consultancy persistOrUpdate(Consultancy consultancy);

    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the categories.
     * @return the list of entities.
     */
    public List<Consultancy> findAll();

    /**
     * Get the "id" category.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Consultancy> findOne(Long id);
}
