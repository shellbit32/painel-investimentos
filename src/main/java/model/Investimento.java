package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "investimento")
@Data
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "ID do cliente é obrigatório")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @NotNull(message = "ID do produto é obrigatório")
    @Column(name = "produto_id", nullable = false)
    private Long produtoId;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.0", message = "Valor deve ser maior que zero")
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull(message = "Prazo é obrigatório")
    @Min(value = 1, message = "Prazo deve ser pelo menos 1 mês")
    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @NotNull(message = "Data do investimento é obrigatória")
    @Column(name = "data_hora_investimento", nullable = false)
    private LocalDateTime dataHora;
}
