package rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseSimulacaoInvestimentoDto {

    private ProdutoValidadoDto produtoValidado;

    private ResultadoSimulacaoDto resultadoSimulacao;

    private String dataSimulacao;
}
