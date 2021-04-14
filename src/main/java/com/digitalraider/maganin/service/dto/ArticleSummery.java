package com.digitalraider.maganin.service.dto;

import com.digitalraider.maganin.domain.Article;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.Instant;


@RegisterForReflection
public class ArticleSummery {
    public Long id;
    public String title;
    public String author;
    public String authorPageURL;
    public String shortDesc;
    public Integer impressions;
    public Integer avgRating;
    public String thumbnail;
    public String writerImageURL;
    public Instant dateLastMod;
    public String articleCategory;

    public ArticleSummery(Article article) {
        this.id = article.id;
        this.title = article.title;
        this.author = article.author;
        this.authorPageURL = article.relatedURL;
        this.shortDesc = article.shortDesc;
        this.impressions = article.impressions;
        this.avgRating = article.avgRating;
        this.thumbnail = article.category.imageURL;
        this.writerImageURL = article.writerImageURL;
        this.dateLastMod = article.dateLastMod;
        this.articleCategory = article.category.name;
    }
}

