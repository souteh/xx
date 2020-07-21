package com.mycompany.myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TypeDeContenu} entity.
 */
public class TypeDeContenuDTO implements Serializable {
    
    private Long id;

    private String denominationAr;

    private String denominationFr;

    @NotNull
    @Size(min = 5, max = 10)
    private String reference;

    @NotNull
    private String code;


    private Long fondDocumentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominationAr() {
        return denominationAr;
    }

    public void setDenominationAr(String denominationAr) {
        this.denominationAr = denominationAr;
    }

    public String getDenominationFr() {
        return denominationFr;
    }

    public void setDenominationFr(String denominationFr) {
        this.denominationFr = denominationFr;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getFondDocumentId() {
        return fondDocumentId;
    }

    public void setFondDocumentId(Long fondDocumentId) {
        this.fondDocumentId = fondDocumentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeDeContenuDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeDeContenuDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeDeContenuDTO{" +
            "id=" + getId() +
            ", denominationAr='" + getDenominationAr() + "'" +
            ", denominationFr='" + getDenominationFr() + "'" +
            ", reference='" + getReference() + "'" +
            ", code='" + getCode() + "'" +
            ", fondDocumentId=" + getFondDocumentId() +
            "}";
    }
}
