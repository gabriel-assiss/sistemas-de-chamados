package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.entity.Funcionario;
import br.com.projeto.chamados.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



    @RestController
    @RequestMapping("/funcionarios")
    public class FuncionarioController {
        private final FuncionarioService funcionarioservice;

        public FuncionarioController(FuncionarioService funcionarioservice) {
            this.funcionarioservice = funcionarioservice;
        }

        @GetMapping
        public ResponseEntity<List<Funcionario>> todos(){
            return ResponseEntity.ok(funcionarioservice.buscarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id){
            return ResponseEntity.of(funcionarioservice.buscarPorId(id));
        }

        @GetMapping("/buscar")
        public ResponseEntity<Funcionario> buscarPorNome(@RequestParam String nome){
            return ResponseEntity.of(funcionarioservice.buscarPorNome(nome));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario){
            return ResponseEntity.of(funcionarioservice.atualizar(id, funcionario));
        }

        @PostMapping
        public ResponseEntity<Funcionario> criar(@RequestBody Funcionario funcionario){
            return ResponseEntity.status(201).body(funcionarioservice.salvar(funcionario));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id){
            funcionarioservice.deletar(id);
            return ResponseEntity.noContent().build();
        }

    }


