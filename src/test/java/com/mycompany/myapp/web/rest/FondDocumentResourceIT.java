package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.XxApp;
import com.mycompany.myapp.domain.FondDocument;
import com.mycompany.myapp.repository.FondDocumentRepository;
import com.mycompany.myapp.service.FondDocumentService;
import com.mycompany.myapp.service.dto.FondDocumentDTO;
import com.mycompany.myapp.service.mapper.FondDocumentMapper;

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
 * Integration tests for the {@link FondDocumentResource} REST controller.
 */
@SpringBootTest(classes = XxApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FondDocumentResourceIT {

    private static final String DEFAULT_DENOMINATION_AR = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_FORMAT_PJ = "AAAAAAAAAA";
    private static final String UPDATED_FORMAT_PJ = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    @Autowired
    private FondDocumentRepository fondDocumentRepository;

    @Autowired
    private FondDocumentMapper fondDocumentMapper;

    @Autowired
    private FondDocumentService fondDocumentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFondDocumentMockMvc;

    private FondDocument fondDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FondDocument createEntity(EntityManager em) {
        FondDocument fondDocument = new FondDocument()
            .denominationAr(DEFAULT_DENOMINATION_AR)
            .denominationFr(DEFAULT_DENOMINATION_FR)
            .formatPj(DEFAULT_FORMAT_PJ)
            .reference(DEFAULT_REFERENCE);
        return fondDocument;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FondDocument createUpdatedEntity(EntityManager em) {
        FondDocument fondDocument = new FondDocument()
            .denominationAr(UPDATED_DENOMINATION_AR)
            .denominationFr(UPDATED_DENOMINATION_FR)
            .formatPj(UPDATED_FORMAT_PJ)
            .reference(UPDATED_REFERENCE);
        return fondDocument;
    }

    @BeforeEach
    public void initTest() {
        fondDocument = createEntity(em);
    }

    @Test
    @Transactional
    public void createFondDocument() throws Exception {
        int databaseSizeBeforeCreate = fondDocumentRepository.findAll().size();
        // Create the FondDocument
        FondDocumentDTO fondDocumentDTO = fondDocumentMapper.toDto(fondDocument);
        restFondDocumentMockMvc.perform(post("/api/fond-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fondDocumentDTO)))
            .andExpect(status().isCreated());

        // Validate the FondDocument in the database
        List<FondDocument> fondDocumentList = fondDocumentRepository.findAll();
        assertThat(fondDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        FondDocument testFondDocument = fondDocumentList.get(fondDocumentList.size() - 1);
        assertThat(testFondDocument.getDenominationAr()).isEqualTo(DEFAULT_DENOMINATION_AR);
        assertThat(testFondDocument.getDenominationFr()).isEqualTo(DEFAULT_DENOMINATION_FR);
        assertThat(testFondDocument.getFormatPj()).isEqualTo(DEFAULT_FORMAT_PJ);
        assertThat(testFondDocument.getReference()).isEqualTo(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    public void createFondDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fondDocumentRepository.findAll().size();

        // Create the FondDocument with an existing ID
        fondDocument.setId(1L);
        FondDocumentDTO fondDocumentDTO = fondDocumentMapper.toDto(fondDocument);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFondDocumentMockMvc.perform(post("/api/fond-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fondDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FondDocument in the database
        List<FondDocument> fondDocumentList = fondDocumentRepository.findAll();
        assertThat(fondDocumentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFondDocuments() throws Exception {
        // Initialize the database
        fondDocumentRepository.saveAndFlush(fondDocument);

        // Get all the fondDocumentList
        restFondDocumentMockMvc.perform(get("/api/fond-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fondDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].denominationAr").value(hasItem(DEFAULT_DENOMINATION_AR)))
            .andExpect(jsonPath("$.[*].denominationFr").value(hasItem(DEFAULT_DENOMINATION_FR)))
            .andExpect(jsonPath("$.[*].formatPj").value(hasItem(DEFAULT_FORMAT_PJ)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)));
    }
    
    @Test
    @Transactional
    public void getFondDocument() throws Exception {
        // Initialize the database
        fondDocumentRepository.saveAndFlush(fondDocument);

        // Get the fondDocument
        restFondDocumentMockMvc.perform(get("/api/fond-documents/{id}", fondDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fondDocument.getId().intValue()))
            .andExpect(jsonPath("$.denominationAr").value(DEFAULT_DENOMINATION_AR))
            .andExpect(jsonPath("$.denominationFr").value(DEFAULT_DENOMINATION_FR))
            .andExpect(jsonPath("$.formatPj").value(DEFAULT_FORMAT_PJ))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE));
    }
    @Test
    @Transactional
    public void getNonExistingFondDocument() throws Exception {
        // Get the fondDocument
        restFondDocumentMockMvc.perform(get("/api/fond-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFondDocument() throws Exception {
        // Initialize the database
        fondDocumentRepository.saveAndFlush(fondDocument);

        int databaseSizeBeforeUpdate = fondDocumentRepository.findAll().size();

        // Update the fondDocument
        FondDocument updatedFondDocument = fondDocumentRepository.findById(fondDocument.getId()).get();
        // Disconnect from session so that the updates on updatedFondDocument are not directly saved in db
        em.detach(updatedFondDocument);
        updatedFondDocument
            .denominationAr(UPDATED_DENOMINATION_AR)
            .denominationFr(UPDATED_DENOMINATION_FR)
            .formatPj(UPDATED_FORMAT_PJ)
            .reference(UPDATED_REFERENCE);
        FondDocumentDTO fondDocumentDTO = fondDocumentMapper.toDto(updatedFondDocument);

        restFondDocumentMockMvc.perform(put("/api/fond-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fondDocumentDTO)))
            .andExpect(status().isOk());

        // Validate the FondDocument in the database
        List<FondDocument> fondDocumentList = fondDocumentRepository.findAll();
        assertThat(fondDocumentList).hasSize(databaseSizeBeforeUpdate);
        FondDocument testFondDocument = fondDocumentList.get(fondDocumentList.size() - 1);
        assertThat(testFondDocument.getDenominationAr()).isEqualTo(UPDATED_DENOMINATION_AR);
        assertThat(testFondDocument.getDenominationFr()).isEqualTo(UPDATED_DENOMINATION_FR);
        assertThat(testFondDocument.getFormatPj()).isEqualTo(UPDATED_FORMAT_PJ);
        assertThat(testFondDocument.getReference()).isEqualTo(UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingFondDocument() throws Exception {
        int databaseSizeBeforeUpdate = fondDocumentRepository.findAll().size();

        // Create the FondDocument
        FondDocumentDTO fondDocumentDTO = fondDocumentMapper.toDto(fondDocument);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFondDocumentMockMvc.perform(put("/api/fond-documents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fondDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FondDocument in the database
        List<FondDocument> fondDocumentList = fondDocumentRepository.findAll();
        assertThat(fondDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFondDocument() throws Exception {
        // Initialize the database
        fondDocumentRepository.saveAndFlush(fondDocument);

        int databaseSizeBeforeDelete = fondDocumentRepository.findAll().size();

        // Delete the fondDocument
        restFondDocumentMockMvc.perform(delete("/api/fond-documents/{id}", fondDocument.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FondDocument> fondDocumentList = fondDocumentRepository.findAll();
        assertThat(fondDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
