package com.duettsoftware.backend.config;

import com.duettsoftware.backend.model.User;
import com.duettsoftware.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
            User admin = new User();
            admin.setNome("Master Admin");
            admin.setEmail("admin@admin.com");
            admin.setSenha(passwordEncoder.encode("admin123")); 
            admin.setCpf("11111111111");
            admin.setPerfil("Administrador");
            userRepository.save(admin);
            System.out.println("✔ Usuário administrador criado: admin@admin.com / admin123");
        }

        if (userRepository.findByEmail("usuario@teste.com").isEmpty()) {
            User usuario = new User();
            usuario.setNome("Usuario normal");
            usuario.setEmail("usuario@teste.com");
            usuario.setSenha(passwordEncoder.encode("usuario123"));
            usuario.setCpf("22222222222");
            usuario.setPerfil("Usuario");
            userRepository.save(usuario);
            System.out.println("✔ Usuário comum criado: usuario@teste.com / usuario123");
        }

        if (userRepository.findByEmail("ana.silva@teste.com").isEmpty()) {
            User ana = new User();
            ana.setNome("Ana Silva");
            ana.setEmail("ana.silva@teste.com");
            ana.setSenha(passwordEncoder.encode("senha123"));
            ana.setCpf("33333333333");
            ana.setPerfil("Usuario");
            userRepository.save(ana);
            System.out.println("✔ Usuário criado: ana.silva@teste.com / senha123");
        }

        if (userRepository.findByEmail("carlos.souza@teste.com").isEmpty()) {
            User carlos = new User();
            carlos.setNome("Carlos Souza");
            carlos.setEmail("carlos.souza@teste.com");
            carlos.setSenha(passwordEncoder.encode("senha123"));
            carlos.setCpf("44444444444");
            carlos.setPerfil("Usuario");
            userRepository.save(carlos);
            System.out.println("✔ Usuário criado: carlos.souza@teste.com / senha123");
        }

        if (userRepository.findByEmail("mariana.lima@teste.com").isEmpty()) {
            User mariana = new User();
            mariana.setNome("Mariana Lima");
            mariana.setEmail("mariana.lima@teste.com");
            mariana.setSenha(passwordEncoder.encode("senha123"));
            mariana.setCpf("55555555555");
            mariana.setPerfil("Usuario");
            userRepository.save(mariana);
            System.out.println("✔ Usuário criado: mariana.lima@teste.com / senha123");
        }
    }
}
