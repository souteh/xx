package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FondDocumentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FondDocumentDTO.class);
        FondDocumentDTO fondDocumentDTO1 = new FondDocumentDTO();
        fondDocumentDTO1.setId(1L);
        FondDocumentDTO fondDocumentDTO2 = new FondDocumentDTO();
        assertThat(fondDocumentDTO1).isNotEqualTo(fondDocumentDTO2);
        fondDocumentDTO2.setId(fondDocumentDTO1.getId());
        assertThat(fondDocumentDTO1).isEqualTo(fondDocumentDTO2);
        fondDocumentDTO2.setId(2L);
        assertThat(fondDocumentDTO1).isNotEqualTo(fondDocumentDTO2);
        fondDocumentDTO1.setId(null);
        assertThat(fondDocumentDTO1).isNotEqualTo(fondDocumentDTO2);
    }
}
