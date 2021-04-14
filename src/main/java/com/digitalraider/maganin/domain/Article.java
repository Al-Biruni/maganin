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
 * A Article.
 */
@Entity
@Table(name = "article")
@Cacheable
@RegisterForReflection
public class Article extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @Column(name = "title")
    public String title;

    @Column(name = "author")
    public String author;

    @Column(name = "related_url")
    public String relatedURL;

    @Column(name = "date_added")
    public Instant dateAdded;

    @Column(name = "short_desc")
    public String shortDesc;

    @Column(name = "long_desc")
    public String longDesc;

    @Column(name = "display")
    public String display;

    @Column(name = "access_level")
    public Integer accessLevel;

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

    @Column(name = "writer_image_url")
    public String writerImageURL;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        return id != null && id.equals(((Article) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", author='" + author + "'" +
            ", relatedURL='" + relatedURL + "'" +
            ", dateAdded='" + dateAdded + "'" +
            ", shortDesc='" + shortDesc + "'" +
            ", longDesc='" + longDesc + "'" +
            ", display='" + display + "'" +
            ", accessLevel=" + accessLevel +
            ", impressions=" + impressions +
            ", avgRating=" + avgRating +
            ", thumbnail='" + thumbnail + "'" +
            ", keywords='" + keywords + "'" +
            ", dateLastMod='" + dateLastMod + "'" +
            "}";
    }

    public Article update() {
        return update(this);
    }

    public Article persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static Article update(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("article can't be null");
        }
        var entity = Article.<Article>findById(article.id);
        if (entity != null) {
            entity.title = article.title;
            entity.author = article.author;
            entity.relatedURL = article.relatedURL;
            entity.dateAdded = article.dateAdded;
            entity.shortDesc = article.shortDesc;
            entity.longDesc = article.longDesc;
            entity.display = article.display;
            entity.accessLevel = article.accessLevel;
            entity.impressions = article.impressions;
            entity.avgRating = article.avgRating;
            entity.thumbnail = article.thumbnail;
            entity.keywords = article.keywords;
            entity.dateLastMod = article.dateLastMod;
        }
        return entity;
    }

    public static Article persistOrUpdate(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("article can't be null");
        }
        if (article.id == null) {
            persist(article);
            return article;
        } else {
            return update(article);
        }
    }


}
