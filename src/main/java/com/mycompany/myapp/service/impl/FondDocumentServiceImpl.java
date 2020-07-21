package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.FondDocumentService;
import com.mycompany.myapp.domain.FondDocument;
import com.mycompany.myapp.repository.FondDocumentRepository;
import com.mycompany.myapp.service.dto.FondDocumentDTO;
import com.mycompany.myapp.service.mapper.FondDocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FondDocument}.
 */
@Service
@Transactional
public class FondDocumentServiceImpl implements FondDocumentService {

    private final Logger log = LoggerFactory.getLogger(FondDocumentServiceImpl.class);

    private final FondDocumentRepository fondDocumentRepository;

    private final FondDocumentMapper fondDocumentMapper;

    public FondDocumentServiceImpl(FondDocumentRepository fondDocumentRepository, FondDocumentMapper fondDocumentMapper) {
        this.fondDocumentRepository = fondDocumentRepository;
        this.fondDocumentMapper = fondDocumentMapper;
    }

    @Override
    public FondDocumentDTO save(FondDocumentDTO fondDocumentDTO) {
        log.debug("Request to save FondDocument : {}", fondDocumentDTO);
        FondDocument fondDocument = fondDocumentMapper.toEntity(fondDocumentDTO);
        fondDocument = fondDocumentRepository.save(fondDocument);
        return fondDocumentMapper.toDto(fondDocument);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FondDocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FondDocuments");
        return fondDocumentRepository.findAll(pageable)
            .map(fondDocumentMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FondDocumentDTO> findOne(Long id) {
        log.debug("Request to get FondDocument : {}", id);
        return fondDocumentRepository.findById(id)
            .map(fondDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FondDocument : {}", id);
        fondDocumentRepository.deleteById(id);
    }
}
