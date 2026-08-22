package br.com.projeto.chamados.security;

import br.com.projeto.chamados.entity.Usuario;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final String secret;
    private final SecretKey key;
    public JwtService() {
        this.secret = System.getenv("JWT_SECRET");
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String gerarToken(Usuario usuario) {
        Instant agora = Instant.now();
        Instant expiracao = agora.plusSeconds(60 * 60 * 2);
        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("role", usuario.getRole().name())
                .expiration(Date.from(expiracao))
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
