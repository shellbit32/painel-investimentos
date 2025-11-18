package rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServicoTelemetriaDto {

    private String nome;

    private Integer quantidadeChamadas;

    private Integer mediaTempoRespostaMs;
}
