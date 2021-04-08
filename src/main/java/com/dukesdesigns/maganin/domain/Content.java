package com.dukesdesigns.maganin.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.json.bind.annotation.JsonbTransient;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A Content.
 */

@Entity(name = "content")
@Table(name = "content")
@Cacheable
@RegisterForReflection

public class Content extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "content_type_id")
    public Integer contentTypeID;

    @Column(name = "title")
    public String title;

    @Column(name = "author")
    public String author;

    @Column(name = "related_url")
    public String relatedURL;

    @Column(name = "date_added")
    public LocalDateTime dateAdded;

    @Column(name = "short_desc")
    public String shortDesc;

    @Column(name = "long_desc")
    public String longDesc;

    @Column(name = "display")
    @JsonbTransient
    public String display;

    @Column(name = "access_level")
    @JsonbTransient
    public Integer accessLevel;

    @Column(name = "expire")
    @JsonbTransient
    public LocalDateTime expire;

    @Column(name = "priority")
    @JsonbTransient
    public Integer priority;

    @Column(name = "impressions")
    public Integer impressions;

    @Column(name = "click_thrus")
    public Integer clickThrus;

    @Column(name = "avg_rating")
    public Integer avgRating;

    @Column(name = "ratings")
    @JsonbTransient
    public Integer ratings;

    @Column(name = "thumbnail")
    public String thumbnail;

    @Column(name = "writer_image_url")
    public String writerImageURL;

    @Column(name = "date_end")
    @JsonbTransient
    public LocalDateTime dateEnd;

    @Column(name = "keywords")
    public String keywords;

    @Column(name = "creations_type")
    public String creationsType;

    @Column(name = "wazn")
    public String wazn;

    @Column(name = "country")
    public String country;

    @Column(name = "date_last_mod")
    public LocalDateTime dateLastMod;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Content)) {
            return false;
        }
        return id != null && id.equals(((Content) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Content{" +
            "id=" + id +
            ", userId=" + userId +
            ", contentTypeID=" + contentTypeID +
            ", title='" + title + "'" +
            ", author='" + author + "'" +
            ", relatedURL='" + relatedURL + "'" +
            ", dateAdded='" + dateAdded + "'" +
            ", shortDesc='" + shortDesc + "'" +
            ", longDesc='" + longDesc + "'" +
            ", display='" + display + "'" +
            ", accessLevel=" + accessLevel +
            ", expire='" + expire + "'" +
            ", priority=" + priority +
            ", impressions=" + impressions +
            ", clickThrus=" + clickThrus +
            ", avgRating=" + avgRating +
            ", ratings=" + ratings +
            ", thumbnail='" + thumbnail + "'" +
            ", image1='" + writerImageURL + "'" +
            ", dateEnd='" + dateEnd + "'" +
            ", keywords='" + keywords + "'" +
            ", creationsType='" + creationsType + "'" +
            ", wazn='" + wazn + "'" +
            ", country='" + country + "'" +
            ", dateLastMod='" + dateLastMod + "'" +
            "}";
    }

    public Content update() {
        return update(this);
    }

    public Content persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static Content update(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("content can't be null");
        }
        var entity = Content.<Content>findById(content.id);
        if (entity != null) {
            entity.userId = content.userId;
            entity.contentTypeID = content.contentTypeID;
            entity.title = content.title;
            entity.author = content.author;
            entity.relatedURL = content.relatedURL;
            entity.dateAdded = content.dateAdded;
            entity.shortDesc = content.shortDesc;
            entity.longDesc = content.longDesc;
            entity.display = content.display;
            entity.accessLevel = content.accessLevel;
            entity.expire = content.expire;
            entity.priority = content.priority;
            entity.impressions = content.impressions;
            entity.clickThrus = content.clickThrus;
            entity.avgRating = content.avgRating;
            entity.ratings = content.ratings;
            entity.thumbnail = content.thumbnail;
            entity.writerImageURL = content.writerImageURL;
            entity.dateEnd = content.dateEnd;
            entity.keywords = content.keywords;
            entity.creationsType = content.creationsType;
            entity.wazn = content.wazn;
            entity.country = content.country;
            entity.dateLastMod = content.dateLastMod;
            entity.category = content.category;
        }
        return entity;
    }

    public static Content persistOrUpdate(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("content can't be null");
        }
        if (content.id == null) {
            persist(content);
            return content;
        } else {
            return update(content);
        }
    }


}