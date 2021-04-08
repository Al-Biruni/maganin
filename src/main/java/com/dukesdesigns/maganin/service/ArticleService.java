package com.dukesdesigns.maganin.service;

import com.dukesdesigns.maganin.service.dto.Article;
import com.dukesdesigns.maganin.service.dto.ArticleSummery;

import java.util.List;

public interface ArticleService {

    List<ArticleSummery> getArticlesSummeryByPage(int pageNum, String sortBy);

    Article getArticleById(Long id);

    List<ArticleSummery> getArticlesSummeryByAuthor(String authorName);

    List<ArticleSummery> getArticlesSummeryByCategoryId(Long categoryId);

    List<ArticleSummery> getArticlesSummeryByCategoryName(String categoryName);
}
