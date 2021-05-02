package com.digitalraider.maganin.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "doctor")
@Cacheable
@RegisterForReflection
public class Doctor  extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "date_added")
    public Instant dateAdded;

    @Column(name = "short_desc")
    public String shortDesc;

    @Column(name = "long_desc")
    public String longDesc;

    @Column(name = "display")
    public String display;

    @Column(name = "impressions")
    public Integer impressions;

    @Column(name = "avg_rating")
    public Integer avgRating;

    @Column(name = "thumbnail")
    public String thumbnail;

    @Column(name = "speciality")
    public String speciality;

    @Column(name = "date_last_mod")
    public Instant dateLastMod;

    @Column(name = "writer_image_url")
    public String writerImageURL;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER )
    public List<PersonalBlog> blogs;

    @OneToMany(mappedBy = "doctor",orphanRemoval = true)
    @JsonbTransient
    public List<Consultancy> consultancies;

    public List<Consultancy> getConsultancies() {
        return Consultancy.list("show",true);
    }
//
//    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER)
//    public List<ConsultancyType> consultancyTypes;
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Doctor)) {
            return false;
        }
        return id!= null && id.equals(((Doctor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Doctor{" +
            "id=" + name+
            ", title='" + name + "'" +
            ", dateAdded='" + dateAdded + "'" +
            ", shortDesc='" + shortDesc + "'" +
            ", longDesc='" + longDesc + "'" +
            ", display='" + display + "'" +
            ", impressions=" + impressions +
            ", avgRating=" + avgRating +
            ", thumbnail='" + thumbnail + "'" +
            ", keywords='" + speciality + "'" +
            ", dateLastMod='" + dateLastMod + "'" +
            "}";
    }

    public Doctor update() {
        return update(this);
    }

    public Doctor persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static Doctor update(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("doctor can't be null");
        }
        var entity = Doctor.<Doctor>findById(doctor.id);
        if (entity != null) {
            entity.id = doctor.id;
            entity.name = doctor.name;
            entity.shortDesc = doctor.shortDesc;
            entity.longDesc = doctor.longDesc;
            entity.display = doctor.display;
            entity.impressions = doctor.impressions;
            entity.avgRating = doctor.avgRating;
            entity.thumbnail = doctor.thumbnail;
            entity.speciality = doctor.speciality;
            entity.dateLastMod = Instant.now();
        }
        return entity;
    }

    public static Doctor persistOrUpdate(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("doctor can't be null");
        }
        if (doctor.id== null) {
            doctor.dateAdded=Instant.now();
            persist(doctor);
            return doctor;
        } else {
            return update(doctor);
        }
    }


}

