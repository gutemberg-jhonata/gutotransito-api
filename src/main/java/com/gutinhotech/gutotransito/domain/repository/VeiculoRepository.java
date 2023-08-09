package com.gutinhotech.gutotransito.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutinhotech.gutotransito.domain.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    public Optional<Veiculo> findByPlaca(String placa);

}
