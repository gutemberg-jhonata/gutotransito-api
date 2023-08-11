package com.gutinhotech.gutotransito.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //@ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    //@JoinColumn(name = "proprietario_id")
    @ManyToOne
    private Proprietario proprietario;

    private String marca;
    private String modelo;
    private String placa;

    //@JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo")
    List<Autuacao> autuacoes = new ArrayList<>();

}
