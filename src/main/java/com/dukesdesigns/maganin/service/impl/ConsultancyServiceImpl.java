package com.dukesdesigns.maganin.service.impl;

import com.dukesdesigns.maganin.domain.Consultancy;
import com.dukesdesigns.maganin.domain.Content;
import com.dukesdesigns.maganin.service.ConsultancyService;
import io.quarkus.panache.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
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
     * Delete the Content by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Content : {}", id);
        Content.findByIdOptional(id).ifPresent(content -> {
            content.delete();
        });
    }

    /**
     * Get one content by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Consultancy> findOne(Long id) {
        log.debug("Request to get Content : {}", id);
        return Consultancy.findByIdOptional(id);
    }

    /**
     * Get all the contents.
     * @return the list of entities.
     */
    @Override
    public List<Consultancy> findAll() {
        log.debug("Request to get all Contents");
        return Consultancy.findAll().page(new Page(100)).firstPage().list();
    }


}
