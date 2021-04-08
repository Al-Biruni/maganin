package com.dukesdesigns.maganin.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cacheable
@RegisterForReflection
public class Category extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "category_type_id")
    public String categoryTypeId;

    @Column(name = "name")
    public String name;

    @Column(name = "parent_id")
    public Long parentId;

    @Column(name = "description")
    public String description;

    @Column(name = "image_url")
    public String imageURL;

    @Column(name = "hide_search")
    public Boolean hideSearch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", categoryTypeId='" + categoryTypeId + "'" +
            ", categoryName='" + name + "'" +
            ", parentId=" + parentId +
            ", description='" + description + "'" +
            ", imageURL='" + imageURL + "'" +
            ", hideSearch='" + hideSearch + "'" +
            "}";
    }

    public Category update() {
        return update(this);
    }

    public Category persistOrUpdate() {
        return persistOrUpdate(this);
    }

    public static Category update(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("category can't be null");
        }
        var entity = Category.<Category>findById(category.id);
        if (entity != null) {
            entity.categoryTypeId = category.categoryTypeId;
            entity.name = category.name;
            entity.parentId = category.parentId;
            entity.description = category.description;
            entity.imageURL = category.imageURL;
            entity.hideSearch = category.hideSearch;
        }
        return entity;
    }

    public static Category persistOrUpdate(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("category can't be null");
        }
        if (category.id == null) {
            persist(category);
            return category;
        } else {
            return update(category);
        }
    }


}
