package com.gutinhotech.gutotransito.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutinhotech.gutotransito.domain.model.Veiculo;
import com.gutinhotech.gutotransito.domain.repository.VeiculoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listar() {
       return veiculoRepository.findAll();
    }
    
    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

}
