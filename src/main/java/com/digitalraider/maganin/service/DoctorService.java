package com.digitalraider.maganin.service;

import com.digitalraider.maganin.domain.Doctor;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import java.util.Optional;

public interface DoctorService {


    /**
     * Save a doctor.
     *
     * @param doctor the entity to save.
     * @return the persisted entity.
     */
    Doctor persistOrUpdate(Doctor doctor);

    /**
     * Delete the "id" doctor.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);

    /**
     * Get all the doctors.
     * @param page the pagination information.
     * @return the list of entities.
     */
    public Paged<Doctor> findAll(Page page, Sort sort);

    /**
     * Get the "id" doctor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */

    Optional<Doctor> findOne(Integer id);

}
