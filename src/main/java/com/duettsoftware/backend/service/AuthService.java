package com.duettsoftware.backend.service;

import com.duettsoftware.backend.model.User;
import com.duettsoftware.backend.repository.UserRepository;
import com.duettsoftware.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String senha) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new Exception("Usuário não encontrado");
        }
        User user = userOptional.get();

        if (!passwordEncoder.matches(senha, user.getSenha())) {
            throw new Exception("Senha inválida");
        }

        return jwtUtil.generateToken(user);
    }
}
