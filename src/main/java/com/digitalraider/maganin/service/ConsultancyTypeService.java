package com.digitalraider.maganin.service;

import com.digitalraider.maganin.domain.ConsultancyType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.digitalraider.maganin.domain.ConsultancyType}.
 */
public interface ConsultancyTypeService {

    /**
     * Save a consultancyType.
     *
     * @param consultancyType the entity to save.
     * @return the persisted entity.
     */
    ConsultancyType persistOrUpdate(ConsultancyType consultancyType);

    /**
     * Delete the "id" consultancyType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the consultancyTypes.
     * @return the list of entities.
     */
    public  List<ConsultancyType> findAll();

    /**
     * Get the "id" consultancyType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConsultancyType> findOne(Long id);



}
