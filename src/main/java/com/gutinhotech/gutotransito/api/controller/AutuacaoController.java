package com.gutinhotech.gutotransito.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gutinhotech.gutotransito.api.assembler.AutuacaoAssembler;
import com.gutinhotech.gutotransito.api.model.input.AutuacaoInput;
import com.gutinhotech.gutotransito.api.model.output.AutuacaoOutput;
import com.gutinhotech.gutotransito.domain.service.RegistroAutuacaoService;
import com.gutinhotech.gutotransito.domain.service.RegistroVeiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final AutuacaoAssembler autuacaoAssembler;
    private final RegistroAutuacaoService registroAutuacaoService;
    private final RegistroVeiculoService registroVeiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoOutput registrar(
        @PathVariable Long veiculoId,
        @Valid @RequestBody final AutuacaoInput autuacaoInput) {
            final var autuacao = autuacaoAssembler.toEntity(autuacaoInput);
            final var autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, autuacao);
            return autuacaoAssembler.toModel(autuacaoRegistrada);
    }

    @GetMapping
    public List<AutuacaoOutput> listar(@PathVariable final Long veiculoId) {
        final var veiculo = registroVeiculoService.buscar(veiculoId);
        return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
    }

}
