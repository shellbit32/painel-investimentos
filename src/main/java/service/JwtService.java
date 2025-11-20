package service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class JwtService {

    public String gerarToken(String username, String... roles) {
        return Jwt.issuer("painel-investimentos")
                .upn(username)
                .groups(new HashSet<>(Arrays.asList(roles)))
                .expiresIn(Duration.ofHours(24))
                .sign();
    }

    public String gerarTokenBasico() {
        return gerarToken("usuario-padrao", "USER");
    }
}