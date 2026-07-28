package br.com.projeto.chamados.repository;

import br.com.projeto.chamados.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ChamadoRepository extends JpaRepository<Chamado,Long> {
    Optional<Chamado> findByProblema(String problema);
}
