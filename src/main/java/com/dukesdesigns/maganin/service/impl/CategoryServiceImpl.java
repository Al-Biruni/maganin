package com.dukesdesigns.maganin.service.impl;

import com.dukesdesigns.maganin.service.CategoryService;
import com.dukesdesigns.maganin.domain.Category;
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
public class CategoryServiceImpl implements CategoryService {

    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    

    @Override
    @Transactional
    public Category persistOrUpdate(Category category) {
        log.debug("Request to save Category : {}", category);
        return Category.persistOrUpdate(category);
    }

    /**
     * Delete the Category by id.
     *
     * @param id the id of the entity.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        Category.findByIdOptional(id).ifPresent(category -> {
            category.delete();
        });
    }

    /**
     * Get one category by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Category> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return Category.findByIdOptional(id);
    }

    /**
     * Get all the categories.
     * @return the list of entities.
     */
    @Override
    public  List<Category> findAll() {
        log.debug("Request to get all Categories");
            return Category.findAll().list();
    }



}
