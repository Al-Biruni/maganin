package com.digitalraider.maganin.service.impl;

import com.digitalraider.maganin.service.ConsultancyService;
import com.digitalraider.maganin.service.dto.ConsultancyDTO;
import com.digitalraider.maganin.service.dto.ConsultancySummery;
import io.quarkus.panache.common.Page;
import com.digitalraider.maganin.service.Paged;
import com.digitalraider.maganin.domain.Consultancy;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Paged<Consultancy> findAll(Page page, Sort sort) {
        log.debug("Request to get all Consultancies");
        return new Paged<>(Consultancy.findAll(sort).page(page));

    }


    @Override

    public Paged<Consultancy> findPublished(Page page , Sort sort, List<Integer> consultancyTypeIds){
        return new Paged<>(
            Consultancy.getPublishedPanacheQuery(consultancyTypeIds,sort).page(page)
        );

    }

    @Override
    public List<ConsultancySummery> findLatestConsultancies() {

       return Consultancy.find("show",Sort.by("date", Sort.Direction.Descending),true).
           range(0,5).stream().map(c -> new ConsultancySummery((Consultancy) c)).
           collect(Collectors.toList());

    }

}
