package com.gutinhotech.gutotransito.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gutinhotech.gutotransito.api.assembler.VeiculoAssembler;
import com.gutinhotech.gutotransito.api.model.VeiculoModel;
import com.gutinhotech.gutotransito.domain.model.Veiculo;
import com.gutinhotech.gutotransito.domain.repository.VeiculoRepository;
import com.gutinhotech.gutotransito.domain.service.RegistroVeiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;
    private final VeiculoAssembler veiculoAssembler;

    @GetMapping
    public List<VeiculoModel> listar() {
       return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }
    
    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
            .map(veiculoAssembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return veiculoAssembler.toModel(registroVeiculoService.cadastrar(veiculo));
    }

}
