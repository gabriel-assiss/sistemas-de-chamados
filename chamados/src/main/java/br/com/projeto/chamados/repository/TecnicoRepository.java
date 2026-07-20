package br.com.projeto.chamados.repository;

import br.com.projeto.chamados.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TecnicoRepository extends JpaRepository<Tecnico,Long> {
Optional<Tecnico> findByNome(String nome);
}
