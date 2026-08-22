package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.dto.CadastroUsuarioDTO;
import br.com.projeto.chamados.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioservice;

    public UsuarioController(UsuarioService usuarioservice) {
        this.usuarioservice = usuarioservice;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody CadastroUsuarioDTO cadastro){
        usuarioservice.cadastroUsuario(cadastro);
        return ResponseEntity.status(201).build();
    }
}
