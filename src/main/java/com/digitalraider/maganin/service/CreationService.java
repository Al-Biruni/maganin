package com.digitalraider.maganin.service;

import com.digitalraider.maganin.domain.Article;
import com.digitalraider.maganin.domain.Creation;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import java.util.Optional;

public interface CreationService {

    Creation persistOrUpdate(Creation article);


    void delete(Long id);


    public Paged<Creation> findAll(Page page, Sort sort);


    Optional<Creation> findOne(Long id);
}
