package br.com.projeto.chamados.security;

import br.com.projeto.chamados.entity.Usuario;
import br.com.projeto.chamados.enums.Role;
import br.com.projeto.chamados.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public AdminInitializer(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {


        if(usuarioRepository.findByEmail("admin@email.com").isEmpty()) {


            Usuario admin = new Usuario();

            admin.setNome("Administrador");
            admin.setEmail("admin@email.com");

            admin.setSenha(
                    passwordEncoder.encode("123456")
            );

            admin.setRole(Role.ADMIN);


            usuarioRepository.save(admin);

        }

    }
}
