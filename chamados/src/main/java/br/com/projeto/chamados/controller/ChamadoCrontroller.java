package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chamado")
public class ChamadoCrontroller {
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/chamados")
    public List<Chamado> Todos(){
        return chamadoService.buscarTodos();
    }
    @GetMapping("/chamado/id/${id}")
    public Optional<Chamado> buscarPorId(@PathVariable Long id){
        return chamadoService.buscarPorId(id);
    }
    @GetMapping("/chamado/nome/${nome}")
    public Optional<Chamado> buscarPorNome(@PathVariable String nome){
        return chamadoService.buscarPorNome(nome);
    }
    @PutMapping("/atuaizar/${id}")
    public Optional<Chamado> atualizar(@PathVariable Long id, @RequestBody Chamado chamado){
        return chamadoService.atualizar(id, chamado);
    }
    @PostMapping("/criar")
    public Chamado criar(@RequestBody Chamado chamado){
        return chamadoService.salvar(chamado);
    }

    @DeleteMapping("/deletar/${id}")
    public void deletar(@PathVariable Long id){
        chamadoService.deletar(id);
    }

}
