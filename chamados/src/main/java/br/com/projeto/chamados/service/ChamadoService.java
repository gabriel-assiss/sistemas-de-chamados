package br.com.projeto.chamados.service;

import br.com.projeto.chamados.dto.ChamadoResponseDTO;
import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.entity.Funcionario;
import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.enums.Role;
import br.com.projeto.chamados.enums.Status;
import br.com.projeto.chamados.repository.ChamadoRepository;
import br.com.projeto.chamados.repository.FuncionarioRepository;
import br.com.projeto.chamados.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;
    private TecnicoRepository tecnicoRepository;
    private ChamadoResponseDTO chamadoResponseDTO;
    private ChamadoService chamadoService;
    private FuncionarioRepository funcionarioRepository;

    public Chamado atribuirTecnico(Long chamadoId, Long tecnicoId) {

        Chamado chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow();

        Tecnico tecnico = tecnicoRepository.findById(tecnicoId)
                .orElseThrow();

        chamado.setTecnico(tecnico);
        chamado.setStatus(Status.NO_RESOLVIDO);

        return chamadoRepository.save(chamado);
    }
    private ChamadoResponseDTO converterParaDTO(Chamado chamado){
            ChamadoResponseDTO chamadoDTO = new ChamadoResponseDTO();
            
            if (chamado.getTecnico() == null) {
                chamadoDTO.setNomeTecnico("não atribuido");
            } else {
                chamado.setStatus(Status.NO_RESOLVIDO);
                chamadoDTO.setNomeTecnico(chamado.getTecnico().getNome());
            }
            chamadoDTO.setId(chamado.getId());
            chamadoDTO.setProblema(chamado.getProblema());
            chamadoDTO.setStatus(chamado.getStatus());
            chamadoDTO.setNomeFuncionario(chamado.getFuncionario().getNome());

            return chamadoDTO;
    }

    public List<ChamadoResponseDTO> buscarTodos() {
        List<Chamado> chamados = chamadoRepository.findAll();
        List<ChamadoResponseDTO> chamadosDTOs = new ArrayList<>();
        for (Chamado chamado : chamados) {
           chamadosDTOs.add(converterParaDTO(chamado));
        }
        return  chamadosDTOs;
    }

    public Optional<ChamadoResponseDTO> buscarPorId(Long id) {

             Optional<Chamado> chamado = chamadoRepository.findById(id);
             if (chamado.isPresent()) {
                 return Optional.of(converterParaDTO(chamado.get()));
             }else {
                 return Optional.empty();
             }

    }

    public Optional<ChamadoResponseDTO> buscarPorProblema(String nome) {
        Optional<Chamado> chamado = chamadoRepository.findByProblema(nome);
        if (chamado.isPresent()) {
            return Optional.of(converterParaDTO(chamado.get()));
        }else{
            return Optional.empty();
        }

    }

    public Optional<List<ChamadoResponseDTO>> buscarChamadosPorUsuario(long id, Role role) {

        if (role == Role.TECNICO) {

            Optional<Tecnico> tecnico = tecnicoRepository.findByUsuarioId(id);

            if (tecnico.isPresent()) {

                List<Chamado> chamados = chamadoRepository.findAll();

                List<ChamadoResponseDTO> chamadosDTOs = new ArrayList<>();

                for (Chamado chamado : chamados) {
                    ChamadoResponseDTO chamadoDTO = converterParaDTO(chamado);
                    chamadosDTOs.add(chamadoDTO);
                }

                return Optional.of(chamadosDTOs);
            }

        } else if (role == Role.FUNCIONARIO) {

            Optional<Funcionario> funcionario = funcionarioRepository.findByUsuarioId(id);

            if (funcionario.isPresent()) {

                List<Chamado> chamados =
                        chamadoRepository.findAllByFuncionarioId(funcionario.get().getId());

                List<ChamadoResponseDTO> chamadosDTOs = new ArrayList<>();

                for (Chamado chamado : chamados) {
                    ChamadoResponseDTO chamadoDTO = converterParaDTO(chamado);
                    chamadosDTOs.add(chamadoDTO);
                }

                return Optional.of(chamadosDTOs);
            }
        }

        return Optional.empty();
    }

    public Chamado salvar(Chamado chamado) {
        if (!chamadoRepository.existsById(chamado.getId())){
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

    public void atualizarStatusParaResolvido(Long id){
        Chamado chamado = chamadoRepository.findById(id).orElse(null);
        chamado.setStatus(Status.RESOLVIDO);
        chamadoRepository.save(chamado);
    }


}
