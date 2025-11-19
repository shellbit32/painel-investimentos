package model;

/*
Haverá rotina de expurgo para esta tabela, que será executada mensalmente.
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetria")
@Data
public class Telemetria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do serviço é obrigatório")
    @Column(name = "nome_servico", nullable = false, length = 100)
    private String nomeServico;

    @NotNull(message = "Tempo de resposta é obrigatório")
    @Column(name = "tempo_resposta_ms", nullable = false)
    private Long tempoRespostaMs;

    @NotNull(message = "Data e hora são obrigatórios")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
}
