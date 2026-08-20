package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.dto.ChamadoResponseDTO;
import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.enums.Role;
import br.com.projeto.chamados.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoservice;

    @GetMapping("/chamados")
    public List<ChamadoResponseDTO> Todos(){
        return chamadoservice.buscarTodos();
    }
    @GetMapping("/chamado/id/{id}")
    public Optional<ChamadoResponseDTO> buscarPorId(@PathVariable Long id){
        return chamadoservice.buscarPorId(id);
    }
    @GetMapping("/chamado/id")
    public Optional<List<ChamadoResponseDTO>> buscarPorId(@RequestBody Long id, Role role){
        return chamadoservice.buscarChamadosPorUsuario(id, role);
    }

    @GetMapping("/chamado/nome/{nome}")
    public Optional<ChamadoResponseDTO> buscarPorProblema(@PathVariable String nome){
        return chamadoservice.buscarPorProblema(nome);
    }
    @PutMapping("/atuaizar/{id}")
    public Optional<Chamado> atualizar(@PathVariable Long id, @RequestBody Chamado chamado){
        return chamadoservice.atualizar(id, chamado);
    }
    @PutMapping("/ChamadoResolvido")
    public void resolvido(@RequestBody Long id){
        chamadoservice.atualizarStatusParaResolvido(id);
    }
    @PostMapping("/criar")

    public Chamado criar(@RequestBody Chamado chamado){
        return chamadoservice.salvar(chamado);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id){
        chamadoservice.deletar(id);
    }


}
