package com.gutinhotech.gutotransito.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gutinhotech.gutotransito.domain.model.Autuacao;

public interface AutuacaoRepository extends JpaRepository<Autuacao, Long> {
    
}
