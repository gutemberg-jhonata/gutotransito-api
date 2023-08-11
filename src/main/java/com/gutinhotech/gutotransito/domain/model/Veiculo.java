package com.gutinhotech.gutotransito.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gutinhotech.gutotransito.domain.exception.NegocioException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
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
    private Status status;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    List<Autuacao> autuacoes = new ArrayList<>();

    public List<Autuacao> getAutuacoes() {
        return Collections.unmodifiableList(autuacoes);
    }

    public Autuacao adicionarAutuacao(final Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        autuacoes.add(autuacao);
        return autuacao;
    }

    public void apreender() {
        if (getStatus().podeApreender()) {
            setStatus(Status.APREENDIDO);
            setDataApreensao(OffsetDateTime.now());
        }

        throw new NegocioException("Veículo não pode ser apreendido");
    }

    public void removerApreensao() {
        if (getStatus().podeRemoverApreensao()) {
            setStatus(Status.REGULAR);
            setDataApreensao(null);
        }

        throw new NegocioException("Veículo não está apreendido");
    }

    public enum Status {
        REGULAR {
            
            @Override
            public boolean podeApreender() {
                return true;
            }
    
            @Override
            public boolean podeRemoverApreensao() {
                return false;
            }
    
        }, 
        APREENDIDO {
            @Override
            public boolean podeApreender() {
                return false;
            }
    
            @Override
            public boolean podeRemoverApreensao() {
                return true;
            }
    
        };
    
        public abstract boolean podeApreender();
        public abstract boolean podeRemoverApreensao();
    }

}
