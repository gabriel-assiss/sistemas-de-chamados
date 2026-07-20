package br.com.projeto.chamados.service;

import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.repository.TecnicoRepository;
import br.com.projeto.chamados.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public List<Tecnico> buscarTodos() {
        return tecnicoRepository.findAll();
    }

    public Optional<Tecnico> buscarPorId(Long id) {
        return tecnicoRepository.findById(id);
    }

    public Optional<Tecnico> buscarPorNome(String nome) {
        return tecnicoRepository.findByNome(nome);
    }

    public Tecnico salvar(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public Optional<Tecnico> atualizar(Long id, Tecnico tecnico) {
        Tecnico tecnicoExistente = tecnicoRepository.findById(id).orElse(null);
        tecnicoExistente.setNome(tecnico.getNome());
        tecnicoExistente.setNivel(tecnico.getNivel());
        return  Optional.of(tecnicoRepository.save(tecnicoExistente));
    }

    public void deletar(Long id) {tecnicoRepository.deleteById(id);
    }
}
