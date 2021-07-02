package com.digitalraider.maganin.domain;

import com.digitalraider.maganin.service.dto.ConsultancyDTO;
import com.digitalraider.maganin.service.dto.ConsultancySummery;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbTransient;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

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
    @GeneratedValue()
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

    @Column(name = "published")
    public Boolean show;

    @Column(name = "impressions")
    public Integer impressions;

    @ManyToOne
    @JoinColumn(name = "consultancy_type_id")
    @JsonbTransient
    public ConsultancyType consultancyType;

    @ManyToOne
    @JoinColumn(name = "consultant_id")
    @JsonbTransient
    public Doctor doctor;

    public Consultancy(ConsultancyDTO consultancyDTO) {
        this.id = consultancyDTO.id;
        this.userId = consultancyDTO.userId;
        this.date = consultancyDTO.dateAdded.toInstant(ZoneOffset.UTC);
        this.name = consultancyDTO.name;
        this.age = consultancyDTO.age;
        this.gender = consultancyDTO.gender;
        this.religion = consultancyDTO.religion;
        this.rel2 = consultancyDTO.rel2;
        this.edu = consultancyDTO.edu;
        this.social = consultancyDTO.social;
        this.econ = consultancyDTO.econ;
        this.hop = consultancyDTO.hop;
        this.job = consultancyDTO.job;
        this.country = consultancyDTO.country;
        this.origin = consultancyDTO.origin;
        this.email = consultancyDTO.email;
        this.title = consultancyDTO.title;
        this.question = consultancyDTO.question;
        this.answer = consultancyDTO.answer;
        this.show = consultancyDTO.show;
        this.doctor=Doctor.findByName(consultancyDTO.doctor);
        this.consultancyType=ConsultancyType.findByArName(consultancyDTO.consultancyType);

    }

    public Consultancy() {

    }


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
            ", show='" + show + "'" +
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
            entity.name = consultancy.name;
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
            entity.show = consultancy.show;
            entity.impressions = consultancy.impressions;
            entity.doctor = consultancy.doctor;
            entity.consultancyType =consultancy.consultancyType;
        }
        return entity;
    }

    public static Consultancy persistOrUpdate(Consultancy consultancy) {
        if (consultancy == null) {
            throw new IllegalArgumentException("consultancy can't be null");
        }
        if (consultancy.id == null) {
            consultancy.date = Instant.now();

            persist(consultancy);
            return consultancy;
        } else {
            return update(consultancy);
        }
    }

    public static Consultancy persistOrUpdate(ConsultancyDTO consultancyDTO) {
        if (consultancyDTO == null) {
            throw new IllegalArgumentException("consultancy can't be null");
        }
        if (consultancyDTO.id == null) {
            consultancyDTO.dateAdded = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
            Consultancy c = new Consultancy(consultancyDTO);

            persist(consultancyDTO);
            return c;
        } else {
            return updateFromDTO(consultancyDTO);
        }
    }

    private static Consultancy updateFromDTO(ConsultancyDTO consultancy) {
        var entity = Consultancy.<Consultancy>findById(consultancy.id);
        if (entity != null) {
            entity.name = consultancy.name;
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
            entity.show = consultancy.show;
            entity.doctor = Doctor.findByName(consultancy.doctor);
            entity.consultancyType = ConsultancyType.findByArName(consultancy.consultancyType);
        }
        return entity;
    }
    public static List<Consultancy> getPublished(Sort sort ){
       if(sort==null)
        return Consultancy.list("show",Sort.by("date"),true);

        return Consultancy.list("show",sort,true);

    }
    public static PanacheQuery<Consultancy> getPublishedPanacheQuery(List<Integer> consultancyTypesIds,Sort sort ){
        PanacheQuery<Consultancy> publishedConsultancies;
        if(sort==null) {
            publishedConsultancies = Consultancy.find("show", Sort.by("date", Sort.Direction.Descending),true);
        }else {
            publishedConsultancies = Consultancy.find("show ", sort,true);
        }


       return publishedConsultancies;
    }
    public static List<ConsultancySummery> getLatest(){
        return Consultancy.getPublished(null).subList(0,5).stream().
            map(c->new ConsultancySummery(c)).
            collect(Collectors.toList());
    }

}
