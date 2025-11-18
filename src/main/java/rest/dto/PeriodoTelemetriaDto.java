package rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeriodoTelemetriaDto {

    private String inicio;

    private String fim;
}
