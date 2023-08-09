package com.gutinhotech.gutotransito.domain.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @ManyToOne
    //@JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;

    @NotBlank
    @Size(max = 20)
    private String marca;

    @NotBlank
    @Size(max = 20)
    private String modelo;

    @NotBlank
    @Size(max = 7)
    private String placa;

    @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime dataApreensao;

    public enum Status {
        REGULAR, APREENDIDO
    }

}
