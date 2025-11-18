package rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseTelemetriaDto {

    private List<ServicoTelemetriaDto> servicos;

    private PeriodoTelemetriaDto periodo;
}
