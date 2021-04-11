package com.digitalraider.maganin.service.impl;

import com.digitalraider.maganin.service.ContentService;
import io.quarkus.panache.common.Page;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.domain.Content;
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
public class ContentServiceImpl implements ContentService {

    private final Logger log = LoggerFactory.getLogger(ContentServiceImpl.class);

    

    @Override
    @Transactional
    public Content persistOrUpdate(Content content) {
        log.debug("Request to save Content : {}", content);
        return Content.persistOrUpdate(content);
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
    public Optional<Content> findOne(Long id) {
        log.debug("Request to get Content : {}", id);
        return Content.findByIdOptional(id);
    }

    /**
     * Get all the contents.
     * @param page the pagination information.
     * @return the list of entities.
     */
    @Override
    public Paged<Content> findAll(Page page) {
        log.debug("Request to get all Contents");
        return new Paged<>(Content.findAll().page(page));    }



}