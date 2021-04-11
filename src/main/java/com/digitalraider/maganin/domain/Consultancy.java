package com.digitalraider.maganin.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.json.bind.annotation.JsonbTransient;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Consultancy.
 */
@Entity
@Table(name = "consultancy")
@Cacheable
@RegisterForReflection
public class Consultancy extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "name")
    public String name;

    @Column(name = "date")
    public Instant date;

    @Column(name = "age")
    public String age;

    @Column(name = "gender")
    public String gender;

    @Column(name = "religion")
    public String religion;

    @Column(name = "rel2")
    public String rel2;

    @Column(name = "edu")
    public String edu;

    @Column(name = "social")
    public String social;

    @Column(name = "econ")
    public String econ;

    @Column(name = "hobbies")
    public String hop;

    @Column(name = "job")
    public String job;

    @Column(name = "country")
    public String country;

    @Column(name = "origin")
    public String origin;

    @Column(name = "email")
    public String email;

    @Column(name = "title")
    public String title;

    @Column(name = "question")
    public String question;

    @Column(name = "answer")
    public String answer;

    @Column(name = "doctor")
    public String doctor;

    @Column(name = "consultant_id")
    public Integer consultantId;

    @Column(name = "show")
    public Boolean show;

    @Column(name = "paid")
    public Boolean paid;

    @Column(name = "impressions")
    public Integer impressions;

    @ManyToOne
    @JoinColumn(name = "consultancy_type_id")
    @JsonbTransient
    public ConsultancyType consultancyType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Consultancy)) {
            return false;
        }
        return id != null && id.equals(((Consultancy) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Consultancy{" +
            "id=" + id +
            ", userId=" + userId +
            ", name='" + name + "'" +
            ", date='" + date + "'" +
            ", age='" + age + "'" +
            ", gender='" + gender + "'" +
            ", religion='" + religion + "'" +
            ", rel2='" + rel2 + "'" +
            ", edu='" + edu + "'" +
            ", social='" + social + "'" +
            ", econ='" + econ + "'" +
            ", hop='" + hop + "'" +
            ", job='" + job + "'" +
            ", country='" + country + "'" +
            ", origin='" + origin + "'" +
            ", email='" + email + "'" +
            ", title='" + title + "'" +
            ", question='" + question + "'" +
            ", answer='" + answer + "'" +
            ", doctor='" + doctor + "'" +
            ", consultantId=" + consultantId +
            ", show='" + show + "'" +
            ", paid='" + paid + "'" +
            ", impressions=" + impressions +
            "}";
    }

    public Consultancy update() {
        return update(this);
    }

    public Consultancy persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static Consultancy update(Consultancy consultancy) {
        if (consultancy == null) {
            throw new IllegalArgumentException("consultancy can't be null");
        }
        var entity = Consultancy.<Consultancy>findById(consultancy.id);
        if (entity != null) {
            entity.userId = consultancy.userId;
            entity.name = consultancy.name;
            entity.date = consultancy.date;
            entity.age = consultancy.age;
            entity.gender = consultancy.gender;
            entity.religion = consultancy.religion;
            entity.rel2 = consultancy.rel2;
            entity.edu = consultancy.edu;
            entity.social = consultancy.social;
            entity.econ = consultancy.econ;
            entity.hop = consultancy.hop;
            entity.job = consultancy.job;
            entity.country = consultancy.country;
            entity.origin = consultancy.origin;
            entity.email = consultancy.email;
            entity.title = consultancy.title;
            entity.question = consultancy.question;
            entity.answer = consultancy.answer;
            entity.doctor = consultancy.doctor;
            entity.consultantId = consultancy.consultantId;
            entity.show = consultancy.show;
            entity.paid = consultancy.paid;
            entity.impressions = consultancy.impressions;
            entity.consultancyType = consultancy.consultancyType;
        }
        return entity;
    }

    public static Consultancy persistOrUpdate(Consultancy consultancy) {
        if (consultancy == null) {
            throw new IllegalArgumentException("consultancy can't be null");
        }
        if (consultancy.id == null) {
            persist(consultancy);
            return consultancy;
        } else {
            return update(consultancy);
        }
    }


}
