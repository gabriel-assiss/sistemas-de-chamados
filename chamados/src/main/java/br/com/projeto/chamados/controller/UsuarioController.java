package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.entity.Usuario;
import br.com.projeto.chamados.service.ChamadoService;
import br.com.projeto.chamados.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioservice;


}
