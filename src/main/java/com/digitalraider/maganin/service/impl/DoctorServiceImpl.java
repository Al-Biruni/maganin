package com.digitalraider.maganin.service.impl;

import com.digitalraider.maganin.domain.Doctor;
import com.digitalraider.maganin.service.DoctorService;
import com.digitalraider.maganin.service.Paged;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class DoctorServiceImpl  implements DoctorService {

    private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);



    @Override
    @Transactional
    public Doctor persistOrUpdate(Doctor doctor) {
        log.debug("Request to save Doctor : {}", doctor);
        return Doctor.persistOrUpdate(doctor);
    }

    /**
     * Delete the Doctor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        log.debug("Request to delete Doctor : {}", id);
        Doctor.findByIdOptional(id).ifPresent(doctor -> {
            doctor.delete();
        });
    }

    /**
     * Get one doctor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Doctor> findOne(Integer id) {
        log.debug("Request to get Doctor : {}", id);
        return Doctor.findByIdOptional(id);
    }

    /**
     * Get all the doctors.
     * @param page the pagination information.
     * @return the list of entities.
     */
    @Override
    public Paged<Doctor> findAll(Page page, Sort sort) {
        log.debug("Request to get all Doctors");
        return new Paged<>(Doctor.findAll(sort).page(page));    }


}
