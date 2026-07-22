package br.com.projeto.chamados.service;

import br.com.projeto.chamados.dto.ChamadoResponseDTO;
import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.repository.ChamadoRepository;
import br.com.projeto.chamados.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;
    private ChamadoResponseDTO chamadoResponseDTO;

    public List<ChamadoResponseDTO> buscarTodos() {
        List<Chamado> chamados = chamadoRepository.findAll();
        List<ChamadoResponseDTO> chamadosDTOs = new ArrayList<>();
        for (Chamado chamado : chamados) {
            ChamadoResponseDTO chamadoResponseDTO = new ChamadoResponseDTO();
            chamadoResponseDTO.setId(chamado.getId());
            chamadoResponseDTO.setProblema(chamado.getProblema());
            chamadoResponseDTO.setStatus(chamado.getStatus());
            chamadoResponseDTO.setNomeFuncionario(chamado.getFuncionario().getNome());
            chamadoResponseDTO.setNomeTecnico(chamado.getTecnico().getNome());
            chamadosDTOs.add(chamadoResponseDTO);
        }
        return  chamadosDTOs;
    }

    public Optional<ChamadoResponseDTO> buscarPorId(Long id) {

             Optional<Chamado> chamado = chamadoRepository.findById(id);
             if (chamado.isPresent()) {
                 ChamadoResponseDTO chamadoDTO = new ChamadoResponseDTO();
                 Chamado chamadoEncontrado = chamado.get();
                 chamadoDTO.setId(chamadoEncontrado.getId());
                 chamadoDTO.setProblema(chamadoEncontrado.getProblema());
                 chamadoDTO.setStatus(chamadoEncontrado.getStatus());
                 chamadoDTO.setNomeFuncionario(chamadoEncontrado.getFuncionario().getNome());
                 if (chamadoEncontrado.getTecnico().getNome()==null){
                     chamadoDTO.setNomeTecnico("não atribuido");
                 }else{
                     chamadoDTO.setNomeTecnico(chamadoEncontrado.getTecnico().getNome());
                 }
                 return Optional.of(chamadoDTO);
             }else  {
                 return Optional.empty();
             }


    }

    public Optional<Chamado> buscarPorNome(String nome) {
        return chamadoRepository.findByTitulo(nome);
    }

    public Chamado salvar(Chamado chamado) {
        if (chamadoRepository.existsById(chamado.getId())){
            return chamadoRepository.save(chamado);
        }else{
            return null;
        }
    }

    public Optional<Chamado> atualizar(Long id,Chamado chamado) {
        Chamado chamadoExistente = chamadoRepository.findById(id).orElse(null);
        chamadoExistente.setProblema(chamado.getProblema());
        return  Optional.of(chamadoRepository.save(chamadoExistente));
    }

    public void deletar(Long id) {
        chamadoRepository.deleteById(id);
    }


}
