package br.com.projeto.chamados.service;

import br.com.projeto.chamados.repository.FuncionarioRepository;
import br.com.projeto.chamados.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

}
