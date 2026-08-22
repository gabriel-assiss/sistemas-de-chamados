package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.dto.LoginUsuarioDTO;
import br.com.projeto.chamados.dto.UsuarioResponseDTO;
import br.com.projeto.chamados.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authservice;

    public AuthController(AuthService authservice) {
        this.authservice = authservice;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginUsuarioDTO login) {
        return ResponseEntity.ok(authservice.autenticar(login));
    }

}
