package com.digitalraider.maganin.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.digitalraider.maganin.TestUtil;
import org.junit.jupiter.api.Test;


public class ConsultancyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Consultancy.class);
        Consultancy consultancy1 = new Consultancy();
        consultancy1.id = 1L;
        Consultancy consultancy2 = new Consultancy();
        consultancy2.id = consultancy1.id;
        assertThat(consultancy1).isEqualTo(consultancy2);
        consultancy2.id = 2L;
        assertThat(consultancy1).isNotEqualTo(consultancy2);
        consultancy1.id = null;
        assertThat(consultancy1).isNotEqualTo(consultancy2);
    }
}
