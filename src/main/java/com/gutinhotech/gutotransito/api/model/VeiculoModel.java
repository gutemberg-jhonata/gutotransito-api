package com.gutinhotech.gutotransito.api.model;

import java.time.OffsetDateTime;

import com.gutinhotech.gutotransito.domain.model.StatusVeiculo;
import com.gutinhotech.gutotransito.domain.model.Veiculo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoModel {
    
    private Long id;
    private String nomeProprietario;
    private String marca;
    private String modelo;
    private String placa;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    public VeiculoModel(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.nomeProprietario = veiculo.getProprietario().getNome();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.placa = veiculo.getPlaca();
        this.status = veiculo.getStatus();
        this.dataCadastro = veiculo.getDataCadastro();
        this.dataApreensao = veiculo.getDataApreensao();
    }

}
