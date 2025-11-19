package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulacao")
@Data
public class Simulacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ID do cliente é obrigatório")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @NotNull(message = "ID do produto é obrigatório")
    @Column(name = "produto_id", nullable = false)
    private Long produtoId;

    @NotNull(message = "Valor investido é obrigatório")
    @DecimalMin(value = "0.0", message = "Valor investido deve ser maior que zero")
    @Column(name = "valor_investido", nullable = false)
    private BigDecimal valorInvestido;

    @NotNull(message = "Prazo em meses é obrigatório")
    @Min(value = 1, message = "Prazo deve ser pelo menos 1 mês")
    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @NotNull(message = "Valor final é obrigatório")
    @DecimalMin(value = "0.0", message = "Valor final deve ser maior que zero")
    @Column(name = "valor_final", nullable = false)
    private BigDecimal valorFinal;

    @NotNull(message = "Rentabilidade efetiva é obrigatória")
    @DecimalMin(value = "0.0", message = "Rentabilidade efetiva deve ser maior ou igual a zero")
    @Column(name = "rentabilidade_efetiva", nullable = false)
    private Double rentabilidadeEfetiva;

    @NotNull(message = "Data da simulação é obrigatória")
    @Column(name = "data_simulacao", nullable = false)
    private LocalDateTime dataSimulacao;
}
