package com.duettsoftware.backend.controller;

import com.duettsoftware.backend.dto.UserDTO;
import com.duettsoftware.backend.model.User;
import com.duettsoftware.backend.repository.UserRepository;
import com.duettsoftware.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    //Cadastro
    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());
        user.setSenha(userDTO.getSenha());
        user.setCpf(userDTO.getCpf());
        user.setPerfil(userDTO.getPerfil());
        return userRepository.save(user);
    }

    //Logar
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            String token = authService.login(userDTO.getEmail(), userDTO.getSenha());
            return ResponseEntity.ok().body(Collections.singletonMap("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/home")
    public String home() {
        return "Hola Mundo!";
    }
}
