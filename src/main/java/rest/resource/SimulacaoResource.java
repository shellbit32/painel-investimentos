package rest.resource;

import dao.ProdutoDAO;
import dao.SimulacaoDAO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Produto;
import model.Simulacao;
import rest.dto.*;
import service.CalculadoraFinanceiraService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/simulacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SimulacaoResource {

    @Inject
    private ProdutoDAO produtoDAO;

    @Inject
    private SimulacaoDAO simulacaoDAO;

    @Inject
    private CalculadoraFinanceiraService calculadoraFinanceiraService;

    @POST
    @Path("/simular-investimento")
    @Transactional
    public Response simularInvestimento(RequestSimulacaoInvestimentoDto dto) {
        Produto produto = produtoDAO.buscarProdutoAdequado(dto.getTipoProduto(), dto.getValor(), dto.getPrazoMeses());

        if (produto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhum produto encontrado que atenda aos critérios informados")
                    .build();
        }

        ProdutoValidadoDto produtoValidado = montarProdutoValidado(produto);
        ResultadoSimulacaoDto resultadoSimulacao = calcularResultadoSimulacao(dto, produto);

        persistirSimulacao(dto, produto, resultadoSimulacao);

        ResponseSimulacaoInvestimentoDto response = ResponseSimulacaoInvestimentoDto.builder()
                .produtoValidado(produtoValidado)
                .resultadoSimulacao(resultadoSimulacao)
                .dataSimulacao(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .build();

        return Response.ok(response).build();
    }

    @GET
    public Response historico() {
        return Response.ok().build();
    }

    @GET
    @Path("/por-produto-dia")
    public Response valoresPorProdutoDia() {
        return Response.ok().build();
    }

    private ProdutoValidadoDto montarProdutoValidado(Produto produto) {
        return ProdutoValidadoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .tipo(produto.getTipo().name())
                .rentabilidade(produto.getRentabilidadeAnual())
                .risco(produto.getRisco().name())
                .build();
    }

    private ResultadoSimulacaoDto calcularResultadoSimulacao(RequestSimulacaoInvestimentoDto dto, Produto produto) {
        BigDecimal valorFinal = calculadoraFinanceiraService.calcularValorFinal(
                dto.getValor(),
                produto.getRentabilidadeAnual(),
                dto.getPrazoMeses()
        );

        Double rentabilidadeEfetiva = calculadoraFinanceiraService.calcularRentabilidadeEfetiva(
                dto.getValor(),
                valorFinal
        );

        return ResultadoSimulacaoDto.builder()
                .valorFinal(valorFinal)
                .rentabilidadeEfetiva(rentabilidadeEfetiva)
                .prazoMeses(dto.getPrazoMeses())
                .build();
    }

    private void persistirSimulacao(RequestSimulacaoInvestimentoDto dto, Produto produto, ResultadoSimulacaoDto resultado) {
        Simulacao simulacao = new Simulacao();
        simulacao.setClienteId(dto.getClienteId());
        simulacao.setProdutoId(produto.getId());
        simulacao.setValorInvestido(dto.getValor());
        simulacao.setPrazoMeses(dto.getPrazoMeses());
        simulacao.setValorFinal(resultado.getValorFinal());
        simulacao.setRentabilidadeEfetiva(resultado.getRentabilidadeEfetiva());
        simulacao.setDataSimulacao(LocalDateTime.now());

        simulacaoDAO.inserir(simulacao);
    }
}