package service;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ApplicationScoped
public class CalculadoraFinanceiraService {

    public BigDecimal calcularValorFinal(BigDecimal valorInicial, Double rentabilidadeAnual, Integer prazoMeses) {
        if (valorInicial == null || rentabilidadeAnual == null || prazoMeses == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }

        double taxaMensal = rentabilidadeAnual / 12.0 / 100.0;
        double fator = Math.pow(1 + taxaMensal, prazoMeses);

        return valorInicial.multiply(BigDecimal.valueOf(fator))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Double calcularRentabilidadeEfetiva(BigDecimal valorInicial, BigDecimal valorFinal) {
        if (valorInicial == null || valorFinal == null || valorInicial.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Valores inválidos para cálculo de rentabilidade");
        }

        return valorFinal.subtract(valorInicial)
                .divide(valorInicial, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .doubleValue();
    }
}