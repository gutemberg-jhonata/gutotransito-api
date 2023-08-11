package com.gutinhotech.gutotransito.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.gutinhotech.gutotransito.domain.exception.NegocioException;
import com.gutinhotech.gutotransito.domain.exception.EntidadeNaoEncontradaException;
import com.gutinhotech.gutotransito.domain.model.Proprietario;
import com.gutinhotech.gutotransito.domain.model.Veiculo;
import com.gutinhotech.gutotransito.domain.repository.VeiculoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {
    
    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public Veiculo buscar(final Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Veículo não encontrado."));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um código.");
        }
        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
            .filter(v -> !v.equals(novoVeiculo))
            .isPresent();
        if (placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa.");
        }
        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());
        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(Veiculo.Status.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }

}
