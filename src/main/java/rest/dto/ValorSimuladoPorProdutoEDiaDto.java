package rest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ValorSimuladoPorProdutoEDiaDto {

    private String produto;

    private String data;

    private Integer quantidadeSimulacoes;

    private BigDecimal mediaValorFinal;
}
