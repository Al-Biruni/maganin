package com.dukesdesigns.maganin.service.impl;

import com.dukesdesigns.maganin.domain.Content;
import com.dukesdesigns.maganin.service.ArticleService;
import com.dukesdesigns.maganin.service.dto.Article;
import com.dukesdesigns.maganin.service.dto.ArticleSummery;
import io.quarkus.panache.common.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.dukesdesigns.maganin.config.Constants.ARTICLE_DB_KEYWORD;

@ApplicationScoped
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Override
    public List<ArticleSummery> getArticlesSummeryByPage(int pageNum,String sortBy) {
        log.debug("Request to articles by page #=" + pageNum);

        return Content.find("creationsType",Sort.by(sortBy).descending(),ARTICLE_DB_KEYWORD).
            page(1, 10).stream().
            map(c -> new ArticleSummery((Content) c)).
            collect(Collectors.toList());
    }

    @Override
    public Article getArticleById(Long id) {
        log.debug("Request to find article with id = " + id);
        Content c = Content.findById(id);
        return new Article(c);
    }

    @Override
    public List<ArticleSummery> getArticlesSummeryByAuthor(String authorName) {
        log.debug("Request to find article  by  authorName = " + authorName);
        HashMap<String,Object> params = new HashMap<>();
        params.put("creationsType",ARTICLE_DB_KEYWORD);
        params.put("authorName",authorName);
        List<Content> articlesByAuthors = Content.find("creationsType = :creationsType and author = :authorName", params).list();
        List<ArticleSummery> articleSummeryDTOSByAuthor = articlesByAuthors.stream().map(content -> new ArticleSummery(content)).collect(Collectors.toList());
        return articleSummeryDTOSByAuthor;
    }

    @Override
    public List<ArticleSummery> getArticlesSummeryByCategoryId(Long categoryId) {
        log.debug("Request to find article  by  categoryId = " + categoryId);
        HashMap<String,Object> params = new HashMap<>();
        params.put("creationsType",ARTICLE_DB_KEYWORD);
        params.put("categoryId",categoryId);
        List<Content> articlesByCategoryId = Content.find("creationsType = :creationsType and category.id = :categoryId", params).list();
        List<ArticleSummery> articleDTOSByCategoryID = articlesByCategoryId.stream().map(content -> new ArticleSummery(content)).collect(Collectors.toList());
        return articleDTOSByCategoryID;
    }

    @Override
    public   List<ArticleSummery> getArticlesSummeryByCategoryName(String categoryName) {
        return null;
    }
}
