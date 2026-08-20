package br.com.projeto.chamados.repository;

import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.entity.Funcionario;
import br.com.projeto.chamados.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
Optional<Funcionario> findByNome(String nome);
    Optional<Funcionario> findByUsuarioId(Long usuarioId);



}
