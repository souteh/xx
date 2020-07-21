package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.XxApp;
import com.mycompany.myapp.domain.TypeDeContenu;
import com.mycompany.myapp.repository.TypeDeContenuRepository;
import com.mycompany.myapp.service.TypeDeContenuService;
import com.mycompany.myapp.service.dto.TypeDeContenuDTO;
import com.mycompany.myapp.service.mapper.TypeDeContenuMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TypeDeContenuResource} REST controller.
 */
@SpringBootTest(classes = XxApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeDeContenuResourceIT {

    private static final String DEFAULT_DENOMINATION_AR = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private TypeDeContenuRepository typeDeContenuRepository;

    @Autowired
    private TypeDeContenuMapper typeDeContenuMapper;

    @Autowired
    private TypeDeContenuService typeDeContenuService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeDeContenuMockMvc;

    private TypeDeContenu typeDeContenu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDeContenu createEntity(EntityManager em) {
        TypeDeContenu typeDeContenu = new TypeDeContenu()
            .denominationAr(DEFAULT_DENOMINATION_AR)
            .denominationFr(DEFAULT_DENOMINATION_FR)
            .reference(DEFAULT_REFERENCE)
            .code(DEFAULT_CODE);
        return typeDeContenu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDeContenu createUpdatedEntity(EntityManager em) {
        TypeDeContenu typeDeContenu = new TypeDeContenu()
            .denominationAr(UPDATED_DENOMINATION_AR)
            .denominationFr(UPDATED_DENOMINATION_FR)
            .reference(UPDATED_REFERENCE)
            .code(UPDATED_CODE);
        return typeDeContenu;
    }

    @BeforeEach
    public void initTest() {
        typeDeContenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeDeContenu() throws Exception {
        int databaseSizeBeforeCreate = typeDeContenuRepository.findAll().size();
        // Create the TypeDeContenu
        TypeDeContenuDTO typeDeContenuDTO = typeDeContenuMapper.toDto(typeDeContenu);
        restTypeDeContenuMockMvc.perform(post("/api/type-de-contenus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeContenuDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeDeContenu in the database
        List<TypeDeContenu> typeDeContenuList = typeDeContenuRepository.findAll();
        assertThat(typeDeContenuList).hasSize(databaseSizeBeforeCreate + 1);
        TypeDeContenu testTypeDeContenu = typeDeContenuList.get(typeDeContenuList.size() - 1);
        assertThat(testTypeDeContenu.getDenominationAr()).isEqualTo(DEFAULT_DENOMINATION_AR);
        assertThat(testTypeDeContenu.getDenominationFr()).isEqualTo(DEFAULT_DENOMINATION_FR);
        assertThat(testTypeDeContenu.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testTypeDeContenu.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createTypeDeContenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeDeContenuRepository.findAll().size();

        // Create the TypeDeContenu with an existing ID
        typeDeContenu.setId(1L);
        TypeDeContenuDTO typeDeContenuDTO = typeDeContenuMapper.toDto(typeDeContenu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeDeContenuMockMvc.perform(post("/api/type-de-contenus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeContenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDeContenu in the database
        List<TypeDeContenu> typeDeContenuList = typeDeContenuRepository.findAll();
        assertThat(typeDeContenuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeDeContenuRepository.findAll().size();
        // set the field null
        typeDeContenu.setReference(null);

        // Create the TypeDeContenu, which fails.
        TypeDeContenuDTO typeDeContenuDTO = typeDeContenuMapper.toDto(typeDeContenu);


        restTypeDeContenuMockMvc.perform(post("/api/type-de-contenus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeContenuDTO)))
            .andExpect(status().isBadRequest());

        List<TypeDeContenu> typeDeContenuList = typeDeContenuRepository.findAll();
        assertThat(typeDeContenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeDeContenuRepository.findAll().size();
        // set the field null
        typeDeContenu.setCode(null);

        // Create the TypeDeContenu, which fails.
        TypeDeContenuDTO typeDeContenuDTO = typeDeContenuMapper.toDto(typeDeContenu);


        restTypeDeContenuMockMvc.perform(post("/api/type-de-contenus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeContenuDTO)))
            .andExpect(status().isBadRequest());

        List<TypeDeContenu> typeDeContenuList = typeDeContenuRepository.findAll();
        assertThat(typeDeContenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeDeContenus() throws Exception {
        // Initialize the database
        typeDeContenuRepository.saveAndFlush(typeDeContenu);

        // Get all the typeDeContenuList
        restTypeDeContenuMockMvc.perform(get("/api/type-de-contenus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeDeContenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].denominationAr").value(hasItem(DEFAULT_DENOMINATION_AR)))
            .andExpect(jsonPath("$.[*].denominationFr").value(hasItem(DEFAULT_DENOMINATION_FR)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getTypeDeContenu() throws Exception {
        // Initialize the database
        typeDeContenuRepository.saveAndFlush(typeDeContenu);

        // Get the typeDeContenu
        restTypeDeContenuMockMvc.perform(get("/api/type-de-contenus/{id}", typeDeContenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeDeContenu.getId().intValue()))
            .andExpect(jsonPath("$.denominationAr").value(DEFAULT_DENOMINATION_AR))
            .andExpect(jsonPath("$.denominationFr").value(DEFAULT_DENOMINATION_FR))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeDeContenu() throws Exception {
        // Get the typeDeContenu
        restTypeDeContenuMockMvc.perform(get("/api/type-de-contenus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeDeContenu() throws Exception {
        // Initialize the database
        typeDeContenuRepository.saveAndFlush(typeDeContenu);

        int databaseSizeBeforeUpdate = typeDeContenuRepository.findAll().size();

        // Update the typeDeContenu
        TypeDeContenu updatedTypeDeContenu = typeDeContenuRepository.findById(typeDeContenu.getId()).get();
        // Disconnect from session so that the updates on updatedTypeDeContenu are not directly saved in db
        em.detach(updatedTypeDeContenu);
        updatedTypeDeContenu
            .denominationAr(UPDATED_DENOMINATION_AR)
            .denominationFr(UPDATED_DENOMINATION_FR)
            .reference(UPDATED_REFERENCE)
            .code(UPDATED_CODE);
        TypeDeContenuDTO typeDeContenuDTO = typeDeContenuMapper.toDto(updatedTypeDeContenu);

        restTypeDeContenuMockMvc.perform(put("/api/type-de-contenus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeContenuDTO)))
            .andExpect(status().isOk());

        // Validate the TypeDeContenu in the database
        List<TypeDeContenu> typeDeContenuList = typeDeContenuRepository.findAll();
        assertThat(typeDeContenuList).hasSize(databaseSizeBeforeUpdate);
        TypeDeContenu testTypeDeContenu = typeDeContenuList.get(typeDeContenuList.size() - 1);
        assertThat(testTypeDeContenu.getDenominationAr()).isEqualTo(UPDATED_DENOMINATION_AR);
        assertThat(testTypeDeContenu.getDenominationFr()).isEqualTo(UPDATED_DENOMINATION_FR);
        assertThat(testTypeDeContenu.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testTypeDeContenu.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeDeContenu() throws Exception {
        int databaseSizeBeforeUpdate = typeDeContenuRepository.findAll().size();

        // Create the TypeDeContenu
        TypeDeContenuDTO typeDeContenuDTO = typeDeContenuMapper.toDto(typeDeContenu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeDeContenuMockMvc.perform(put("/api/type-de-contenus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeContenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDeContenu in the database
        List<TypeDeContenu> typeDeContenuList = typeDeContenuRepository.findAll();
        assertThat(typeDeContenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeDeContenu() throws Exception {
        // Initialize the database
        typeDeContenuRepository.saveAndFlush(typeDeContenu);

        int databaseSizeBeforeDelete = typeDeContenuRepository.findAll().size();

        // Delete the typeDeContenu
        restTypeDeContenuMockMvc.perform(delete("/api/type-de-contenus/{id}", typeDeContenu.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeDeContenu> typeDeContenuList = typeDeContenuRepository.findAll();
        assertThat(typeDeContenuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
