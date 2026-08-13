package br.com.projeto.chamados.service;

import br.com.projeto.chamados.dto.LoginUsuarioDTO;
import br.com.projeto.chamados.dto.UsuarioResponseDTO;
import br.com.projeto.chamados.entity.Usuario;
import br.com.projeto.chamados.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager , JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UsuarioResponseDTO autenticar(LoginUsuarioDTO loginUsuarioDTO) {
        Authentication autenticacao =
                new UsernamePasswordAuthenticationToken(
                        loginUsuarioDTO.getEmail(),
                        loginUsuarioDTO.getSenha()
                );
                Authentication autenticado = authenticationManager.authenticate(
                        autenticacao
                );
        Usuario usuario = (Usuario) autenticado.getPrincipal();
        String token = jwtService.gerarToken(usuario);

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        usuarioResponseDTO.setToken(token);
        usuarioResponseDTO.setEmail(usuario.getEmail());
        usuarioResponseDTO.setNome(usuario.getNome());
        usuarioResponseDTO.setRole(usuario.getRole().name());


        return usuarioResponseDTO;

    }
}
