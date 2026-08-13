package br.com.projeto.chamados.service;

import br.com.projeto.chamados.dto.CadastroUsuarioDTO;
import br.com.projeto.chamados.entity.Funcionario;
import br.com.projeto.chamados.entity.Tecnico;
import br.com.projeto.chamados.entity.Usuario;
import br.com.projeto.chamados.enums.Role;
import br.com.projeto.chamados.repository.FuncionarioRepository;
import br.com.projeto.chamados.repository.TecnicoRepository;
import br.com.projeto.chamados.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private final PasswordEncoder passwordEncoder;


    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAll(){
       return usuarioRepository.findAll();
    }
    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }
    @Transactional
    public void cadastroUsuario(CadastroUsuarioDTO cadastroUsuario){
        Usuario usuario = new Usuario();
        usuario.setNome(cadastroUsuario.getNome());
        usuario.setEmail(cadastroUsuario.getEmail());
        usuario.setSenha( passwordEncoder.encode(cadastroUsuario.getSenha()));
        usuario.setRole(cadastroUsuario.getRole());
        if(usuarioRepository.findByEmail(cadastroUsuario.getEmail()).isPresent()){
            throw new RuntimeException("E-mail já cadastrado.");
        }else{
            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            if (cadastroUsuario.getRole()== Role.FUNCIONARIO){
                Funcionario funcionario = new Funcionario();

                funcionario.setUsuario(usuarioSalvo);
                funcionario.setCargo(cadastroUsuario.getCargo());
                funcionario.setNome(usuarioSalvo.getNome());
                funcionarioRepository.save(funcionario);


            } else if (cadastroUsuario.getRole()== Role.TECNICO) {
                Tecnico  tecnico = new Tecnico();

                tecnico.setUsuario(usuarioSalvo);
                tecnico.setCargo(cadastroUsuario.getCargo());
                tecnico.setNome(usuarioSalvo.getNome());
                tecnicoRepository.save(tecnico);


            }

        }
    }

}
