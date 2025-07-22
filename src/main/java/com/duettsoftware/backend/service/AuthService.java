package com.duettsoftware.backend.service;

import com.duettsoftware.backend.model.User;
import com.duettsoftware.backend.repository.UserRepository;
import com.duettsoftware.backend.security.JwtUtil;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public org.springframework.security.core.userdetails.User loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getSenha(),
            Collections.singletonList(new SimpleGrantedAuthority(user.getPerfil()))
        );
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }   

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
