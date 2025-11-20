package rest.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @GET
    @Path("/perfil-risco/{clienteId}")
    public Response obterPerfilRisco(@PathParam("clienteId") Long clienteId) {
        return Response.ok().build();
    }
}
