package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TypeDeContenuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeDeContenu.class);
        TypeDeContenu typeDeContenu1 = new TypeDeContenu();
        typeDeContenu1.setId(1L);
        TypeDeContenu typeDeContenu2 = new TypeDeContenu();
        typeDeContenu2.setId(typeDeContenu1.getId());
        assertThat(typeDeContenu1).isEqualTo(typeDeContenu2);
        typeDeContenu2.setId(2L);
        assertThat(typeDeContenu1).isNotEqualTo(typeDeContenu2);
        typeDeContenu1.setId(null);
        assertThat(typeDeContenu1).isNotEqualTo(typeDeContenu2);
    }
}
