package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TypeDeContenu;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeDeContenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDeContenuRepository extends JpaRepository<TypeDeContenu, Long> {
}
