package rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class HistoricoSimulacaoRealizadaDto {

    private Long id;

    private Long clienteId;

    private String produto;

    private BigDecimal valorInvestido;

    private BigDecimal valorFinal;

    private Integer prazoMeses;

    private String dataSimulacao;

}
