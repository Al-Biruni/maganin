package com.digitalraider.maganin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.digitalraider.maganin.TestUtil;
import org.junit.jupiter.api.Test;


public class ConsultancyTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultancyType.class);
        ConsultancyType consultancyType1 = new ConsultancyType();
        consultancyType1.id = 1L;
        ConsultancyType consultancyType2 = new ConsultancyType();
        consultancyType2.id = consultancyType1.id;
        assertThat(consultancyType1).isEqualTo(consultancyType2);
        consultancyType2.id = 2L;
        assertThat(consultancyType1).isNotEqualTo(consultancyType2);
        consultancyType1.id = null;
        assertThat(consultancyType1).isNotEqualTo(consultancyType2);
    }
}
