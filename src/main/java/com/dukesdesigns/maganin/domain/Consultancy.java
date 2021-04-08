package com.dukesdesigns.maganin.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultancy")
@Cacheable
@RegisterForReflection
public class Consultancy extends PanacheEntityBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name="date")
    public LocalDateTime dateAdded;
    @Column(name = "name")
    public String name;
    @Column(name = "age")
    public String ageRange;
    @Column(name = "religion")
    public String religion;
    @Column(name = "rel2")
    public String rel2;
    @Column(name = "edu")
    public String education;
    @Column(name = "social")
    public String social;
    @Column(name = "hobbies")
    public String hobbies;
    @Column(name = "job")
    public String job;
    @Column(name = "country")
    public String country;
    @Column(name = "origin")
    public String origin;
    @Column(name = "email")
    public String email;
    @Column(name = "user_id")
    public Integer userId;
    @Column(name = "title")
    public String title;
    @Column(name = "question")
    public String question;
    @Column(name = "answer")
    public String answer;
    @Column(name = "doctor")
    public String doctor;
    @Column(name = "consult_type_id")
    public Integer consultTypeId;
    @Column(name = "consultant_id")
    public Long consultantId;
    @Column(name = "show")
    public Boolean show;
    @Column(name = "paid")
    public String paid;
    @Column(name = "impressions")
    public Integer impressions;



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
            ", title='" + title + "'" +
            ", doctor='" + doctor + "'" +

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
        var entity = consultancy.<Consultancy>findById(consultancy.id);
        if (entity != null) {



            entity.userId = consultancy.userId;
            entity.doctor = consultancy.doctor;
            entity.title = consultancy.title;
            entity.ageRange = consultancy.ageRange;
            entity.consultantId = consultancy.consultantId;
            entity.dateAdded = consultancy.dateAdded;
            entity.question = consultancy.question;
            entity.answer = consultancy.answer;
            entity.show = consultancy.show;
            entity.paid = consultancy.paid;
            entity.religion = consultancy.religion;
            entity.rel2 = consultancy.rel2;
            entity.impressions = consultancy.impressions;
            entity.social = consultancy.social;
            entity.hobbies = consultancy.hobbies;
            entity.education = consultancy.education;
            entity.email = consultancy.email;
            entity.country = consultancy.country;
            entity.name = consultancy.name;
            entity.origin = consultancy.origin;
            entity.consultTypeId = consultancy.consultTypeId;
            entity.job = consultancy.job;
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
