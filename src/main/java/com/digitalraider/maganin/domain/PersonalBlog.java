package com.digitalraider.maganin.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
@Entity
@Table(name = "personal_blog")
@Cacheable
@RegisterForReflection
public class PersonalBlog extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @Column(name = "title")
    public String title;

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

    @Column(name = "keywords")
    public String keywords;

    @Column(name = "date_last_mod")
    public Instant dateLastMod;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    @ManyToOne
    @JoinColumn(name = "doctor_name")
    @JsonbTransient
    public Doctor doctor;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Comment> comments;
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonalBlog)) {
            return false;
        }
        return id != null && id.equals(((PersonalBlog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PersonalBlog{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", dateAdded='" + dateAdded + "'" +
            ", shortDesc='" + shortDesc + "'" +
            ", longDesc='" + longDesc + "'" +
            ", display='" + display + "'" +
            ", doctor=" + doctor.name +
            ", impressions=" + impressions +
            ", avgRating=" + avgRating +
            ", thumbnail='" + thumbnail + "'" +
            ", keywords='" + keywords + "'" +
            ", dateLastMod='" + dateLastMod + "'" +
            "}";
    }

    public PersonalBlog update() {
        return update(this);
    }

    public PersonalBlog persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static PersonalBlog update(PersonalBlog personalBlog) {
        if (personalBlog == null) {
            throw new IllegalArgumentException("personalBlog can't be null");
        }
        var entity = PersonalBlog.<PersonalBlog>findById(personalBlog.id);
        if (entity != null) {
            entity.title = personalBlog.title;
            entity.doctor = personalBlog.doctor;
            entity.shortDesc = personalBlog.shortDesc;
            entity.longDesc = personalBlog.longDesc;
            entity.display = personalBlog.display;
            entity.impressions = personalBlog.impressions;
            entity.avgRating = personalBlog.avgRating;
            entity.thumbnail = personalBlog.thumbnail;
            entity.keywords = personalBlog.keywords;
            entity.dateLastMod = Instant.now();
        }
        return entity;
    }

    public static PersonalBlog persistOrUpdate(PersonalBlog personalBlog) {
        if (personalBlog == null) {
            throw new IllegalArgumentException("personalBlog can't be null");
        }
        if (personalBlog.id == null) {
            personalBlog.dateAdded=Instant.now();
            persist(personalBlog);
            return personalBlog;
        } else {
            return update(personalBlog);
        }
    }


}
