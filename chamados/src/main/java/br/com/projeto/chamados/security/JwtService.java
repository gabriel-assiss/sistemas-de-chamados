package br.com.projeto.chamados.security;

import br.com.projeto.chamados.entity.Usuario;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.nio.charset.StandardCharsets;
@Service
public class JwtService {
    private final String secret;
    private final SecretKey key;
    public JwtService() {
        this.secret = System.getenv("JWT_SECRET");
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String gerarToken(Usuario usuario) {

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("role", usuario.getRole().name())
                .signWith(key)
                .compact();
    }

    public String extrairEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
