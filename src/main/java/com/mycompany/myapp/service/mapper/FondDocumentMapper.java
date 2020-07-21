package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.FondDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FondDocument} and its DTO {@link FondDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FondDocumentMapper extends EntityMapper<FondDocumentDTO, FondDocument> {


    @Mapping(target = "typesContenus", ignore = true)
    @Mapping(target = "removeTypesContenu", ignore = true)
    FondDocument toEntity(FondDocumentDTO fondDocumentDTO);

    default FondDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        FondDocument fondDocument = new FondDocument();
        fondDocument.setId(id);
        return fondDocument;
    }
}
