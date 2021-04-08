package com.dukesdesigns.maganin.service.dto;

import com.dukesdesigns.maganin.domain.Content;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;

@RegisterForReflection
public class Article {
    public Long id;
    public String title;
    public String author;
    public String authorPageURL;
    public String shortDesc;
    public String longDesc;
    public Integer impressions;
    public Integer avgRating;
    public String thumbnail;
    public String writerImageURL;
    public String keywords;
    public LocalDateTime dateLastMod;
    public String articleCategory;

    public Article(){

    }
   public Article(Content content) {
        this.id = content.id;
        this.title = content.title;
        this.author = content.author;
        this.authorPageURL = content.relatedURL;
        this.shortDesc = content.shortDesc;
        this.longDesc = content.longDesc;
        this.impressions = content.impressions;
        this.avgRating = content.avgRating;
        this.thumbnail = content.thumbnail;
        this.writerImageURL = content.writerImageURL;
        this.keywords = content.keywords;
        this.dateLastMod = content.dateLastMod;
        this.articleCategory = content.category.name;
    }

}
