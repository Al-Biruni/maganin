package com.digitalraider.maganin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.digitalraider.maganin.TestUtil;
import org.junit.jupiter.api.Test;


public class ContentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Content.class);
        Content content1 = new Content();
        content1.id = 1L;
        Content content2 = new Content();
        content2.id = content1.id;
        assertThat(content1).isEqualTo(content2);
        content2.id = 2L;
        assertThat(content1).isNotEqualTo(content2);
        content1.id = null;
        assertThat(content1).isNotEqualTo(content2);
    }
}
