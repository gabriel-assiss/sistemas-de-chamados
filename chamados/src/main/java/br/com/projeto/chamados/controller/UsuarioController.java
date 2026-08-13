package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.dto.CadastroUsuarioDTO;
import br.com.projeto.chamados.entity.Usuario;
import br.com.projeto.chamados.service.ChamadoService;
import br.com.projeto.chamados.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioservice;

    @PostMapping("/cadastro")
    public void cadastrarUsuario(@RequestBody CadastroUsuarioDTO cadastro){
        usuarioservice.cadastroUsuario(cadastro);
    }
}
