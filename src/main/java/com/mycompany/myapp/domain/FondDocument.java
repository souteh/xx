package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FondDocument.
 */
@Entity
@Table(name = "fond_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FondDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "denomination_ar")
    private String denominationAr;

    @Pattern(regexp = "[A-Z]+")
    @Column(name = "denomination_fr")
    private String denominationFr;

    @Column(name = "format_pj")
    private String formatPj;

    @Column(name = "reference")
    private String reference;

    @OneToMany(mappedBy = "fondDocument")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TypeDeContenu> typesContenus = new HashSet<>();

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

    public FondDocument denominationAr(String denominationAr) {
        this.denominationAr = denominationAr;
        return this;
    }

    public void setDenominationAr(String denominationAr) {
        this.denominationAr = denominationAr;
    }

    public String getDenominationFr() {
        return denominationFr;
    }

    public FondDocument denominationFr(String denominationFr) {
        this.denominationFr = denominationFr;
        return this;
    }

    public void setDenominationFr(String denominationFr) {
        this.denominationFr = denominationFr;
    }

    public String getFormatPj() {
        return formatPj;
    }

    public FondDocument formatPj(String formatPj) {
        this.formatPj = formatPj;
        return this;
    }

    public void setFormatPj(String formatPj) {
        this.formatPj = formatPj;
    }

    public String getReference() {
        return reference;
    }

    public FondDocument reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<TypeDeContenu> getTypesContenus() {
        return typesContenus;
    }

    public FondDocument typesContenus(Set<TypeDeContenu> typeDeContenus) {
        this.typesContenus = typeDeContenus;
        return this;
    }

    public FondDocument addTypesContenu(TypeDeContenu typeDeContenu) {
        this.typesContenus.add(typeDeContenu);
        typeDeContenu.setFondDocument(this);
        return this;
    }

    public FondDocument removeTypesContenu(TypeDeContenu typeDeContenu) {
        this.typesContenus.remove(typeDeContenu);
        typeDeContenu.setFondDocument(null);
        return this;
    }

    public void setTypesContenus(Set<TypeDeContenu> typeDeContenus) {
        this.typesContenus = typeDeContenus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FondDocument)) {
            return false;
        }
        return id != null && id.equals(((FondDocument) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FondDocument{" +
            "id=" + getId() +
            ", denominationAr='" + getDenominationAr() + "'" +
            ", denominationFr='" + getDenominationFr() + "'" +
            ", formatPj='" + getFormatPj() + "'" +
            ", reference='" + getReference() + "'" +
            "}";
    }
}
