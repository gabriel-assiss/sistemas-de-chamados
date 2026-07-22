package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.dto.ChamadoResponseDTO;
import br.com.projeto.chamados.entity.Chamado;
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
    @GetMapping("/chamado/nome/{nome}")
    public Optional<Chamado> buscarPorNome(@PathVariable String nome){
        return chamadoservice.buscarPorNome(nome);
    }
    @PutMapping("/atuaizar/{id}")
    public Optional<Chamado> atualizar(@PathVariable Long id, @RequestBody Chamado chamado){
        return chamadoservice.atualizar(id, chamado);
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
