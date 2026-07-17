package br.com.projeto.chamados.repository;

import br.com.projeto.chamados.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico,Long> {

}
