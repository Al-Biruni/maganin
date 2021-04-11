package com.digitalraider.maganin.service.impl;

import com.digitalraider.maganin.service.ConsultancyTypeService;
import com.digitalraider.maganin.domain.ConsultancyType;
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
public class ConsultancyTypeServiceImpl implements ConsultancyTypeService {

    private final Logger log = LoggerFactory.getLogger(ConsultancyTypeServiceImpl.class);

    

    @Override
    @Transactional
    public ConsultancyType persistOrUpdate(ConsultancyType consultancyType) {
        log.debug("Request to save ConsultancyType : {}", consultancyType);
        return ConsultancyType.persistOrUpdate(consultancyType);
    }

    /**
     * Delete the ConsultancyType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete ConsultancyType : {}", id);
        ConsultancyType.findByIdOptional(id).ifPresent(consultancyType -> {
            consultancyType.delete();
        });
    }

    /**
     * Get one consultancyType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ConsultancyType> findOne(Long id) {
        log.debug("Request to get ConsultancyType : {}", id);
        return ConsultancyType.findByIdOptional(id);
    }

    /**
     * Get all the consultancyTypes.
     * @return the list of entities.
     */
    @Override
    public  List<ConsultancyType> findAll() {
        log.debug("Request to get all ConsultancyTypes");
            return ConsultancyType.findAll().list();
    }



}
