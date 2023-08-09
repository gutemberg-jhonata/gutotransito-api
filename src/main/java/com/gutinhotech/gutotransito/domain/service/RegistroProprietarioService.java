package com.gutinhotech.gutotransito.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gutinhotech.gutotransito.domain.exception.NegocioException;
import com.gutinhotech.gutotransito.domain.model.Proprietario;
import com.gutinhotech.gutotransito.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {
    
    private final ProprietarioRepository proprietarioRepository;

    public Proprietario buscar(Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
            .orElseThrow(() -> new NegocioException("Proprietário não encontrado."));
    }

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
            .filter(p -> !p.equals(proprietario))
            .isPresent();
        if (emailEmUso) {
            throw new NegocioException("Já existe um proprietário cadastrado com este email.");
        }
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

}
