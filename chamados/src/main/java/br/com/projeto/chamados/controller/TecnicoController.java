package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {
    private final TecnicoService tecnicoservice;

    public TecnicoController(TecnicoService tecnicoservice) {
        this.tecnicoservice = tecnicoservice;
    }

    @GetMapping
    public ResponseEntity<List<Tecnico>> todos(){
        return ResponseEntity.ok(tecnicoservice.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable Long id){
        return ResponseEntity.of(tecnicoservice.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Tecnico> buscarPorNome(@RequestParam String nome){
        return ResponseEntity.of(tecnicoservice.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> atualizar(@PathVariable Long id, @RequestBody Tecnico tecnico){
        return ResponseEntity.of(tecnicoservice.atualizar(id, tecnico));
    }

    @PostMapping
    public ResponseEntity<Tecnico> criar(@RequestBody Tecnico tecnico){
        return ResponseEntity.status(201).body(tecnicoservice.salvar(tecnico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        tecnicoservice.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
