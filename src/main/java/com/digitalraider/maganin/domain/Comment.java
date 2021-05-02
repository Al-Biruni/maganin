package com.digitalraider.maganin.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Table(name ="comment")
@Entity
public class Comment  extends PanacheEntityBase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "name")
    public String name;

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    public String email;

    @Column(name = "post_date")
    public Instant postDate;

    @Column(name = "body")
    public String body;

    @Column(name ="user_id")
    public Integer userId;

    @ManyToOne
    @JsonbTransient
    public Content content;

    public void setId(Integer id) {
        this.id = id;
    }

    public Comment() {
    }

}
