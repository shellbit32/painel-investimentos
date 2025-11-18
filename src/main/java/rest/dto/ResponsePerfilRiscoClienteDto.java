package rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponsePerfilRiscoClienteDto {

    private Long clienteId;

    private String perfil;

    private Integer pontuacao;

    private String descricao;
}
