package rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoValidadoDto {

    private Long id;

    private String nome;

    private String tipo;

    private Double rentabilidade;

    private String risco;
}
