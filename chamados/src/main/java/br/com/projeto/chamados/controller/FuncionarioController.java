package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.entity.Funcionario;
import br.com.projeto.chamados.service.FuncionarioService;
import br.com.projeto.chamados.entity.Funcionario;
import br.com.projeto.chamados.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



    @RestController
    @RequestMapping("/funcionario")
    public class FuncionarioController {
        @Autowired
        private FuncionarioService funcionarioService;

        @GetMapping("/funcionarios")
        public List<Funcionario> Todos(){
            return funcionarioService.buscarTodos();
        }
        @GetMapping("/funcionario/id/${id}")
        public Optional<Funcionario> buscarPorId(@PathVariable Long id){
            return funcionarioService.buscarPorId(id);
        }
        @GetMapping("/funcionario/nome/${nome}")
        public Optional<Funcionario> buscarPorNome(@PathVariable String nome){
            return funcionarioService.buscarPorNome(nome);
        }
        @PutMapping("/atuaizar/${id}")
        public Optional<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario){
            return funcionarioService.atualizar(id, funcionario);
        }
        @PostMapping("/criar")
        public Funcionario criar(@RequestBody Funcionario funcionario){
            return funcionarioService.salvar(funcionario);
        }

        @DeleteMapping("/deletar/${id}")
        public void deletar(@PathVariable Long id){
            funcionarioService.deletar(id);
        }

    }


