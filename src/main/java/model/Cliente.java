package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Pontuação de risco deve ser no mínimo 0")
    @Max(value = 100, message = "Pontuação de risco deve ser no máximo 100")
    @Column(name = "pontuacao_risco")
    private Integer pontuacaoRisco;
}
