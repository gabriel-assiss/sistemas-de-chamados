package br.com.projeto.chamados.service;

import br.com.projeto.chamados.entity.Funcionario;
import br.com.projeto.chamados.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> buscarPorNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> atualizar(Long id, Funcionario funcionario) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElse(null);
        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setCargo(funcionario.getCargo());
        return  Optional.of(funcionarioRepository.save(funcionarioExistente));
    }

    public void deletar(Long id) {funcionarioRepository.deleteById(id);
    }
}
