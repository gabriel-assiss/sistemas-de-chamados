package br.com.projeto.chamados.repository;

import br.com.projeto.chamados.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

}
