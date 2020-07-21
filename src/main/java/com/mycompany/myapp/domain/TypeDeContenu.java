package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TypeDeContenu.
 */
@Entity
@Table(name = "type_de_contenu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeDeContenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "denomination_ar")
    private String denominationAr;

    @Column(name = "denomination_fr")
    private String denominationFr;

    @NotNull
    @Size(min = 5, max = 10)
    @Column(name = "reference", length = 10, nullable = false)
    private String reference;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @ManyToOne
    @JsonIgnoreProperties(value = "typesContenus", allowSetters = true)
    private FondDocument fondDocument;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominationAr() {
        return denominationAr;
    }

    public TypeDeContenu denominationAr(String denominationAr) {
        this.denominationAr = denominationAr;
        return this;
    }

    public void setDenominationAr(String denominationAr) {
        this.denominationAr = denominationAr;
    }

    public String getDenominationFr() {
        return denominationFr;
    }

    public TypeDeContenu denominationFr(String denominationFr) {
        this.denominationFr = denominationFr;
        return this;
    }

    public void setDenominationFr(String denominationFr) {
        this.denominationFr = denominationFr;
    }

    public String getReference() {
        return reference;
    }

    public TypeDeContenu reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCode() {
        return code;
    }

    public TypeDeContenu code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FondDocument getFondDocument() {
        return fondDocument;
    }

    public TypeDeContenu fondDocument(FondDocument fondDocument) {
        this.fondDocument = fondDocument;
        return this;
    }

    public void setFondDocument(FondDocument fondDocument) {
        this.fondDocument = fondDocument;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeDeContenu)) {
            return false;
        }
        return id != null && id.equals(((TypeDeContenu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeDeContenu{" +
            "id=" + getId() +
            ", denominationAr='" + getDenominationAr() + "'" +
            ", denominationFr='" + getDenominationFr() + "'" +
            ", reference='" + getReference() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
