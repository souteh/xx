package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FondDocumentMapperTest {

    private FondDocumentMapper fondDocumentMapper;

    @BeforeEach
    public void setUp() {
        fondDocumentMapper = new FondDocumentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fondDocumentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fondDocumentMapper.fromId(null)).isNull();
    }
}
