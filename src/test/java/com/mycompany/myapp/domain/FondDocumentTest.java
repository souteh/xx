package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class FondDocumentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FondDocument.class);
        FondDocument fondDocument1 = new FondDocument();
        fondDocument1.setId(1L);
        FondDocument fondDocument2 = new FondDocument();
        fondDocument2.setId(fondDocument1.getId());
        assertThat(fondDocument1).isEqualTo(fondDocument2);
        fondDocument2.setId(2L);
        assertThat(fondDocument1).isNotEqualTo(fondDocument2);
        fondDocument1.setId(null);
        assertThat(fondDocument1).isNotEqualTo(fondDocument2);
    }
}
