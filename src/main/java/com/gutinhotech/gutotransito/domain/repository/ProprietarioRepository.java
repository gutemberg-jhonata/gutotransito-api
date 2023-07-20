package com.gutinhotech.gutotransito.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutinhotech.gutotransito.domain.model.Proprietario;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
}
