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
        private FuncionarioService funcionarioservice;

        @GetMapping("/funcionarios")
        public List<Funcionario> Todos(){
            return funcionarioservice.buscarTodos();
        }
        @GetMapping("/funcionario/id/{id}")
        public Optional<Funcionario> buscarPorId(@PathVariable Long id){
            return funcionarioservice.buscarPorId(id);
        }
        @GetMapping("/funcionario/nome/{nome}")
        public Optional<Funcionario> buscarPorNome(@PathVariable String nome){
            return funcionarioservice.buscarPorNome(nome);
        }
        @PutMapping("/atuaizar/{id}")
        public Optional<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario){
            return funcionarioservice.atualizar(id, funcionario);
        }
        @PostMapping("/criar")
        public Funcionario criar(@RequestBody Funcionario funcionario){
            return funcionarioservice.salvar(funcionario);
        }

        @DeleteMapping("/deletar/{id}")
        public void deletar(@PathVariable Long id){
            funcionarioservice.deletar(id);
        }

    }


