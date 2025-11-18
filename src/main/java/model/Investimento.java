package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import model.enums.TipoProdutoEnum;
import java.time.LocalDate;

@Entity
@Table(name = "investimento")
@Data
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "ID do cliente é obrigatório")
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @NotNull(message = "Tipo do produto é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoProdutoEnum tipo;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.0", message = "Valor deve ser maior que zero")
    @Column(nullable = false)
    private Double valor;

    @NotNull(message = "Rentabilidade é obrigatória")
    @Column(nullable = false)
    private Double rentabilidade;

    @NotNull(message = "Data do investimento é obrigatória")
    @Column(name = "data_investimento", nullable = false)
    private LocalDate data;
}
