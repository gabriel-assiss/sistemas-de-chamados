package br.com.projeto.chamados.service;

import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

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
        tecnicoExistente.setCargo(tecnico.getCargo());
        return  Optional.of(tecnicoRepository.save(tecnicoExistente));
    }

    public void deletar(Long id) {tecnicoRepository.deleteById(id);
    }
}
