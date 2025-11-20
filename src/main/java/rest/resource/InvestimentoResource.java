package rest.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/investimentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvestimentoResource {

    @GET
    @Path("/{clienteId}")
    public Response listarInvestimentosCliente(@PathParam("clienteId") Long clienteId) {
        return Response.ok().build();
    }
}
