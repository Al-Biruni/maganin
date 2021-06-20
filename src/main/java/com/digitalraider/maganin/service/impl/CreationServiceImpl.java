package com.digitalraider.maganin.service.impl;

import com.digitalraider.maganin.domain.Creation;
import com.digitalraider.maganin.service.CreationService;
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
public class CreationServiceImpl implements CreationService {
    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);



    @Override
    @Transactional
    public Creation persistOrUpdate(Creation article) {
        log.debug("Request to save Creation : {}", article);
        return Creation.persistOrUpdate(article);
    }

    /**
     * Delete the Article by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Creation : {}", id);
        Creation.findByIdOptional(id).ifPresent(article -> {
            article.delete();
        });
    }

    /**
     * Get one article by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Creation> findOne(Long id) {
        log.debug("Request to get Article : {}", id);
        return Creation.findByIdOptional(id);
    }

    /**
     * Get all the articles.
     * @param page the pagination information.
     * @return the list of entities.
     */
    @Override
    public Paged<Creation> findAll(Page page, Sort sort) {
        log.debug("Request to get all Articles");
        return new Paged<>(Creation.findAll(sort).page(page));    }
}
