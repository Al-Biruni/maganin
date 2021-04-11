package com.digitalraider.maganin.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.json.bind.annotation.JsonbTransient;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ConsultancyType.
 */
@Entity
@Table(name = "consultancy_type")
@Cacheable
@RegisterForReflection
public class ConsultancyType extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "type")
    public String type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConsultancyType)) {
            return false;
        }
        return id != null && id.equals(((ConsultancyType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ConsultancyType{" +
            "id=" + id +
            ", type='" + type + "'" +
            "}";
    }

    public ConsultancyType update() {
        return update(this);
    }

    public ConsultancyType persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static ConsultancyType update(ConsultancyType consultancyType) {
        if (consultancyType == null) {
            throw new IllegalArgumentException("consultancyType can't be null");
        }
        var entity = ConsultancyType.<ConsultancyType>findById(consultancyType.id);
        if (entity != null) {
            entity.type = consultancyType.type;
        }
        return entity;
    }

    public static ConsultancyType persistOrUpdate(ConsultancyType consultancyType) {
        if (consultancyType == null) {
            throw new IllegalArgumentException("consultancyType can't be null");
        }
        if (consultancyType.id == null) {
            persist(consultancyType);
            return consultancyType;
        } else {
            return update(consultancyType);
        }
    }


}