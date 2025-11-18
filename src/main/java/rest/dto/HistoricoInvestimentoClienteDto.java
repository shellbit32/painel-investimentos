package rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class HistoricoInvestimentoClienteDto {

    private Long id;

    private String tipo;

    private BigDecimal valor;

    private Double rentabilidade;

    private String data;
}
