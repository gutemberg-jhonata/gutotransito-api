package com.gutinhotech.gutotransito.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutinhotech.gutotransito.domain.model.Proprietario;
import com.gutinhotech.gutotransito.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietatioController {
    
    /*@PersistenceContext
    private EntityManager manager;*/
    private final ProprietarioRepository proprietarioRepository;

    public List<Proprietario> listar() {
        /*TypedQuery<Proprietario> query = manager
            .createQuery("FROM Proprietario", Proprietario.class);
        return query.getResultList();*/
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
        /*Optional<Proprietario> proprietario = proprietarioRepository.findById(proprietarioId);
        
        if (proprietario.isPresent()) {
            return ResponseEntity.ok(proprietario.get());
        }

        return ResponseEntity.notFound().build();*/
        
        return proprietarioRepository.findById(proprietarioId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

}
