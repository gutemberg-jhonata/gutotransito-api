package com.gutinhotech.gutotransito.domain.service;

import org.springframework.stereotype.Service;

import com.gutinhotech.gutotransito.domain.model.Autuacao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {
    
    private final RegistroVeiculoService registroVeiculoService;
    //private final AutuacaoRepository autuacaoRepository;

    @Transactional
    public Autuacao registrar(final Long veiculoId, final Autuacao autuacao) {
        final var veiculo = registroVeiculoService.buscar(veiculoId);
        return veiculo.adicionarAutuacao(autuacao);
        //veiculoRepository.save(veiculo);
    }
    
}
