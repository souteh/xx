package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TypeDeContenuDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TypeDeContenu}.
 */
public interface TypeDeContenuService {

    /**
     * Save a typeDeContenu.
     *
     * @param typeDeContenuDTO the entity to save.
     * @return the persisted entity.
     */
    TypeDeContenuDTO save(TypeDeContenuDTO typeDeContenuDTO);

    /**
     * Get all the typeDeContenus.
     *
     * @return the list of entities.
     */
    List<TypeDeContenuDTO> findAll();


    /**
     * Get the "id" typeDeContenu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeDeContenuDTO> findOne(Long id);

    /**
     * Delete the "id" typeDeContenu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
