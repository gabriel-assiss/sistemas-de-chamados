package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tecnico")
public class TecnicoController {
    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping("/tecnicos")
    public List<Tecnico> Todos(){
        return tecnicoService.buscarTodos();
    }
    @GetMapping("/tecnico/id/${id}")
    public Optional<Tecnico> buscarPorId(@PathVariable Long id){
        return tecnicoService.buscarPorId(id);
    }
    @GetMapping("/tecnico/nome/${nome}")
    public Optional<Tecnico> buscarPorNome(@PathVariable String nome){
        return tecnicoService.buscarPorNome(nome);
    }
    @PutMapping("/atuaizar/${id}")
    public Optional<Tecnico> atualizar(@PathVariable Long id, @RequestBody Tecnico tecnico){
        return tecnicoService.atualizar(id, tecnico);
    }
    @PostMapping("/criar")
    public Tecnico criar(@RequestBody Tecnico tecnico){
        return tecnicoService.salvar(tecnico);
    }

    @DeleteMapping("/deletar/${id}")
    public void deletar(@PathVariable Long id){
        tecnicoService.deletar(id);
    }
}
