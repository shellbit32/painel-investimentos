package rest.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @GET
    @Path("/produtos-recomendados/{perfil}")
    public Response listarProdutosRecomendados(@PathParam("perfil") String perfilStr) {
        return Response.ok().build();
    }

}
