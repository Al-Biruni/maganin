package com.digitalraider.maganin.service.impl;

import com.digitalraider.maganin.service.ConsultancyService;
import io.quarkus.panache.common.Page;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.domain.Consultancy;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class ConsultancyServiceImpl implements ConsultancyService {

    private final Logger log = LoggerFactory.getLogger(ConsultancyServiceImpl.class);

    

    @Override
    @Transactional
    public Consultancy persistOrUpdate(Consultancy consultancy) {
        log.debug("Request to save Consultancy : {}", consultancy);
        return Consultancy.persistOrUpdate(consultancy);
    }

    /**
     * Delete the Consultancy by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Consultancy : {}", id);
        Consultancy.findByIdOptional(id).ifPresent(consultancy -> {
            consultancy.delete();
        });
    }

    /**
     * Get one consultancy by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Consultancy> findOne(Long id) {
        log.debug("Request to get Consultancy : {}", id);
        return Consultancy.findByIdOptional(id);
    }

    /**
     * Get all the consultancies.
     * @param page the pagination information.
     * @return the list of entities.
     */
    @Override
    public Paged<Consultancy> findAll(Page page) {
        log.debug("Request to get all Consultancies");
        return new Paged<>(Consultancy.findAll().page(page));    }



}