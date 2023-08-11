package com.gutinhotech.gutotransito.api.model.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutuacaoOutput {
    
    private Long id;
    private String descricao;
    private BigDecimal valorMulta;
    private OffsetDateTime dataOcorrencia;

}
