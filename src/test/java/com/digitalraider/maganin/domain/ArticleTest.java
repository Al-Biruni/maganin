package com.digitalraider.maganin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.digitalraider.maganin.TestUtil;
import org.junit.jupiter.api.Test;


public class ArticleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Article.class);
        Article article1 = new Article();
        article1.id = 1L;
        Article article2 = new Article();
        article2.id = article1.id;
        assertThat(article1).isEqualTo(article2);
        article2.id = 2L;
        assertThat(article1).isNotEqualTo(article2);
        article1.id = null;
        assertThat(article1).isNotEqualTo(article2);
    }
}
