package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import enums.TipoProdutoEnum;
import enums.TipoRiscoEnum;
import enums.LiquidezEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto")
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nome;

    @NotNull(message = "Tipo do produto é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoProdutoEnum tipo;

    @NotNull(message = "Rentabilidade anual é obrigatória")
    @DecimalMin(value = "0.0", message = "Rentabilidade deve ser maior ou igual a zero")
    @DecimalMax(value = "100.0", message = "Rentabilidade deve ser no máximo 100%")
    @Column(name = "rentabilidade_anual", nullable = false)
    private Double rentabilidadeAnual;

    @NotNull(message = "Prazo mínimo é obrigatório")
    @Min(value = 1, message = "Prazo mínimo deve ser pelo menos 1 mês")
    @Column(name = "prazo_minimo_meses", nullable = false)
    private Integer prazoMinimoMeses;

    @NotNull(message = "Prazo máximo é obrigatório")
    @Min(value = 1, message = "Prazo máximo deve ser pelo menos 1 mês")
    @Column(name = "prazo_maximo_meses", nullable = false)
    private Integer prazoMaximoMeses;

    @NotNull(message = "Valor mínimo é obrigatório")
    @DecimalMin(value = "0.0", message = "Valor mínimo deve ser maior ou igual a zero")
    @Column(name = "valor_minimo", nullable = false)
    private BigDecimal valorMinimo;

    @NotNull(message = "Risco é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoRiscoEnum risco;

    @NotNull(message = "Liquidez é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LiquidezEnum liquidez;

    private Boolean ativo = true;

    @Column(name = "data_inativacao")
    private LocalDateTime dataInativacao;
}
