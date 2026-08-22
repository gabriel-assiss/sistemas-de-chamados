package br.com.projeto.chamados.controller;

import br.com.projeto.chamados.dto.ChamadoResponseDTO;
import br.com.projeto.chamados.entity.Chamado;
import br.com.projeto.chamados.entity.Usuario;
import br.com.projeto.chamados.service.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {
    private final ChamadoService chamadoservice;

    public ChamadoController(ChamadoService chamadoservice) {
        this.chamadoservice = chamadoservice;
    }

    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> todos(){
        return ResponseEntity.ok(chamadoservice.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.of(chamadoservice.buscarPorId(id));
    }

    @GetMapping("/meus")
    public ResponseEntity<List<ChamadoResponseDTO>> buscarMeusChamados(Authentication authentication){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.of(chamadoservice.buscarChamadosPorUsuario(usuario.getId(), usuario.getRole()));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ChamadoResponseDTO> buscarPorProblema(@RequestParam String problema){
        return ResponseEntity.of(chamadoservice.buscarPorProblema(problema));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chamado> atualizar(@PathVariable Long id, @RequestBody Chamado chamado){
        return ResponseEntity.of(chamadoservice.atualizar(id, chamado));
    }

    @PatchMapping("/{id}/resolver")
    public ResponseEntity<Void> resolvido(@PathVariable Long id){
        chamadoservice.atualizarStatusParaResolvido(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Chamado> criar(@RequestBody Chamado chamado){
        return ResponseEntity.status(201).body(chamadoservice.salvar(chamado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        chamadoservice.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
