package com.gutinhotech.gutotransito.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gutinhotech.gutotransito.api.model.input.AutuacaoInput;
import com.gutinhotech.gutotransito.api.model.output.AutuacaoOutput;
import com.gutinhotech.gutotransito.domain.model.Autuacao;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {
    
    private final ModelMapper modelMapper;

    public Autuacao toEntity(final AutuacaoInput autuacaoInput) {
        return modelMapper.map(autuacaoInput, Autuacao.class);
    }

    public AutuacaoOutput toModel(final Autuacao autuacao) {
        return modelMapper.map(autuacao, AutuacaoOutput.class);
    }

    public List<AutuacaoOutput> toCollectionModel(final List<Autuacao> autuacoes) {
        return autuacoes.stream()
            .map(this::toModel)
            .toList();
    }

}
