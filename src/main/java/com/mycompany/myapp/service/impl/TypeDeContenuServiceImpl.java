package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TypeDeContenuService;
import com.mycompany.myapp.domain.TypeDeContenu;
import com.mycompany.myapp.repository.TypeDeContenuRepository;
import com.mycompany.myapp.service.dto.TypeDeContenuDTO;
import com.mycompany.myapp.service.mapper.TypeDeContenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TypeDeContenu}.
 */
@Service
@Transactional
public class TypeDeContenuServiceImpl implements TypeDeContenuService {

    private final Logger log = LoggerFactory.getLogger(TypeDeContenuServiceImpl.class);

    private final TypeDeContenuRepository typeDeContenuRepository;

    private final TypeDeContenuMapper typeDeContenuMapper;

    public TypeDeContenuServiceImpl(TypeDeContenuRepository typeDeContenuRepository, TypeDeContenuMapper typeDeContenuMapper) {
        this.typeDeContenuRepository = typeDeContenuRepository;
        this.typeDeContenuMapper = typeDeContenuMapper;
    }

    @Override
    public TypeDeContenuDTO save(TypeDeContenuDTO typeDeContenuDTO) {
        log.debug("Request to save TypeDeContenu : {}", typeDeContenuDTO);
        TypeDeContenu typeDeContenu = typeDeContenuMapper.toEntity(typeDeContenuDTO);
        typeDeContenu = typeDeContenuRepository.save(typeDeContenu);
        return typeDeContenuMapper.toDto(typeDeContenu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeDeContenuDTO> findAll() {
        log.debug("Request to get all TypeDeContenus");
        return typeDeContenuRepository.findAll().stream()
            .map(typeDeContenuMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeDeContenuDTO> findOne(Long id) {
        log.debug("Request to get TypeDeContenu : {}", id);
        return typeDeContenuRepository.findById(id)
            .map(typeDeContenuMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeDeContenu : {}", id);
        typeDeContenuRepository.deleteById(id);
    }
}
