package br.com.projeto.chamados.service;

import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.repository.ChamadoRepository;
import br.com.projeto.chamados.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    public List<Chamado> buscarTodos() {
        return chamadoRepository.findAll();
    }

    public Optional<Chamado> buscarPorId(Long id) {
        return chamadoRepository.findById(id);
    }

    public Optional<Chamado> buscarPorNome(String nome) {
        return chamadoRepository.findByTitulo(nome);
    }

    public Chamado salvar(Chamado chamado) {
        return chamadoRepository.save(chamado);
    }

    public Optional<Chamado> atualizar(Long id,Chamado chamado) {
        Chamado chamadoExistente = chamadoRepository.findById(id).orElse(null);
        chamadoExistente.setTitulo(chamado.getTitulo());
        chamadoExistente.setProblema(chamado.getProblema());
        return  Optional.of(chamadoRepository.save(chamadoExistente));
    }

    public void deletar(Long id) {
        chamadoRepository.deleteById(id);
    }


}
