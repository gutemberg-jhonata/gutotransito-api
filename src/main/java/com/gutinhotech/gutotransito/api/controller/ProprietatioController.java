package com.gutinhotech.gutotransito.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutinhotech.gutotransito.domain.model.Proprietario;
import com.gutinhotech.gutotransito.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ProprietatioController {
    
    /*@PersistenceContext
    private EntityManager manager;*/
    private final ProprietarioRepository proprietarioRepository;

    @GetMapping("/proprietarios")
    public List<Proprietario> listar() {
        /*TypedQuery<Proprietario> query = manager
            .createQuery("FROM Proprietario", Proprietario.class);
        return query.getResultList();*/
        return proprietarioRepository.findAll();
    }

}
