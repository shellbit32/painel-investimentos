package rest.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.JwtService;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    private JwtService jwtService;

    @POST
    @Path("/token")
    @PermitAll
    public Response gerarToken() {
        String token = jwtService.gerarTokenBasico();
        return Response.ok()
                .entity(new TokenResponse(token))
                .build();
    }

    public record TokenResponse(String token) {}
}