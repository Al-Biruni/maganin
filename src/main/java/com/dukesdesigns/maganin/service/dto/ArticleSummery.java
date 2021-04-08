package com.dukesdesigns.maganin.service.dto;

import com.dukesdesigns.maganin.domain.Content;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;


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
    public LocalDateTime dateLastMod;
    public String articleCategory;

    public ArticleSummery() {

    }

    public ArticleSummery(Content content) {
        this.id = content.id;
        this.title = content.title;
        this.author = content.author;
        this.authorPageURL = content.relatedURL;
        this.shortDesc = content.shortDesc;
        this.impressions = content.impressions;
        this.avgRating = content.avgRating;
        this.thumbnail = content.category.imageURL;
        this.writerImageURL = content.writerImageURL;
        this.dateLastMod = content.dateLastMod;
        this.articleCategory = content.category.name;
    }
}

