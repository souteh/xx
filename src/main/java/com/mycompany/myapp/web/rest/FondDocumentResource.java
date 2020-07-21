package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.FondDocumentService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.FondDocumentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.FondDocument}.
 */
@RestController
@RequestMapping("/api")
public class FondDocumentResource {

    private final Logger log = LoggerFactory.getLogger(FondDocumentResource.class);

    private static final String ENTITY_NAME = "fondDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FondDocumentService fondDocumentService;

    public FondDocumentResource(FondDocumentService fondDocumentService) {
        this.fondDocumentService = fondDocumentService;
    }

    /**
     * {@code POST  /fond-documents} : Create a new fondDocument.
     *
     * @param fondDocumentDTO the fondDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fondDocumentDTO, or with status {@code 400 (Bad Request)} if the fondDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fond-documents")
    public ResponseEntity<FondDocumentDTO> createFondDocument(@Valid @RequestBody FondDocumentDTO fondDocumentDTO) throws URISyntaxException {
        log.debug("REST request to save FondDocument : {}", fondDocumentDTO);
        if (fondDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new fondDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FondDocumentDTO result = fondDocumentService.save(fondDocumentDTO);
        return ResponseEntity.created(new URI("/api/fond-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fond-documents} : Updates an existing fondDocument.
     *
     * @param fondDocumentDTO the fondDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fondDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the fondDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fondDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fond-documents")
    public ResponseEntity<FondDocumentDTO> updateFondDocument(@Valid @RequestBody FondDocumentDTO fondDocumentDTO) throws URISyntaxException {
        log.debug("REST request to update FondDocument : {}", fondDocumentDTO);
        if (fondDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FondDocumentDTO result = fondDocumentService.save(fondDocumentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fondDocumentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fond-documents} : get all the fondDocuments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fondDocuments in body.
     */
    @GetMapping("/fond-documents")
    public ResponseEntity<List<FondDocumentDTO>> getAllFondDocuments(Pageable pageable) {
        log.debug("REST request to get a page of FondDocuments");
        Page<FondDocumentDTO> page = fondDocumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fond-documents/:id} : get the "id" fondDocument.
     *
     * @param id the id of the fondDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fondDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fond-documents/{id}")
    public ResponseEntity<FondDocumentDTO> getFondDocument(@PathVariable Long id) {
        log.debug("REST request to get FondDocument : {}", id);
        Optional<FondDocumentDTO> fondDocumentDTO = fondDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fondDocumentDTO);
    }

    /**
     * {@code DELETE  /fond-documents/:id} : delete the "id" fondDocument.
     *
     * @param id the id of the fondDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fond-documents/{id}")
    public ResponseEntity<Void> deleteFondDocument(@PathVariable Long id) {
        log.debug("REST request to delete FondDocument : {}", id);
        fondDocumentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
