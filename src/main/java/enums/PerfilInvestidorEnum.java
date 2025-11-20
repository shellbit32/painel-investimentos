package enums;

public enum PerfilInvestidorEnum {
    CONSERVADOR("Perfil conservador com baixa movimentação e foco em liquidez e segurança."),
    MODERADO("Perfil equilibrado entre segurança e rentabilidade."),
    AGRESSIVO("Perfil agressivo com busca por alta rentabilidade e aceitação de maior risco.");

    private final String descricao;

    PerfilInvestidorEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o perfil do investidor baseado na pontuação de risco
     * @param pontuacao Pontuação de risco (0-100)
     * @return Perfil do investidor correspondente
     */
    public static PerfilInvestidorEnum obterPorPontuacao(int pontuacao) {
        if (pontuacao >= 0 && pontuacao <= 40) {
            return CONSERVADOR;
        } else if (pontuacao >= 41 && pontuacao <= 70) {
            return MODERADO;
        } else if (pontuacao >= 71 && pontuacao <= 100) {
            return AGRESSIVO;
        }
        throw new IllegalArgumentException("Pontuação de risco deve estar entre 0 e 100");
    }
}
