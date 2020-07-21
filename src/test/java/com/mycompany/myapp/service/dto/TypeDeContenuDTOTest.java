package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TypeDeContenuDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeDeContenuDTO.class);
        TypeDeContenuDTO typeDeContenuDTO1 = new TypeDeContenuDTO();
        typeDeContenuDTO1.setId(1L);
        TypeDeContenuDTO typeDeContenuDTO2 = new TypeDeContenuDTO();
        assertThat(typeDeContenuDTO1).isNotEqualTo(typeDeContenuDTO2);
        typeDeContenuDTO2.setId(typeDeContenuDTO1.getId());
        assertThat(typeDeContenuDTO1).isEqualTo(typeDeContenuDTO2);
        typeDeContenuDTO2.setId(2L);
        assertThat(typeDeContenuDTO1).isNotEqualTo(typeDeContenuDTO2);
        typeDeContenuDTO1.setId(null);
        assertThat(typeDeContenuDTO1).isNotEqualTo(typeDeContenuDTO2);
    }
}
