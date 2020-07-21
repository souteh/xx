package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeDeContenuMapperTest {

    private TypeDeContenuMapper typeDeContenuMapper;

    @BeforeEach
    public void setUp() {
        typeDeContenuMapper = new TypeDeContenuMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeDeContenuMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeDeContenuMapper.fromId(null)).isNull();
    }
}
