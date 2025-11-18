package rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RequestSimulacaoInvestimentoDto {
    private Long clienteId;

    private BigDecimal valor;

    private Integer prazoMeses;

    private String tipoProduto;
}
