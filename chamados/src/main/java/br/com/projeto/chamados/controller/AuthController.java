package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.dto.LoginUsuarioDTO;
import br.com.projeto.chamados.dto.UsuarioResponseDTO;
import br.com.projeto.chamados.entity.Usuario;
import br.com.projeto.chamados.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authservice;

    @PostMapping("/login")
    public UsuarioResponseDTO login(@RequestBody LoginUsuarioDTO login) {
        return authservice.autenticar(login);
    }

}
