package com.duettsoftware.backend.controller;

import com.duettsoftware.backend.dto.LoginDTO;
import com.duettsoftware.backend.dto.UserDTO;
import com.duettsoftware.backend.model.User;
import com.duettsoftware.backend.repository.UserRepository;
import com.duettsoftware.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        if (userDTO.getNome() == null || userDTO.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("O nome é obrigatório.");
        }

        if (!userDTO.getEmail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$")) {
            return ResponseEntity.badRequest().body("E-mail inválido.");
        }

        if (userDTO.getSenha() == null || userDTO.getSenha().length() < 6) {
            return ResponseEntity.badRequest().body("A senha deve ter pelo menos 6 caracteres.");
        }

        if (!userDTO.getCpf().matches("\\d{11}")) {
            return ResponseEntity.badRequest().body("CPF inválido. Deve conter 11 dígitos.");
        }

        if (!userDTO.getPerfil().equalsIgnoreCase("Administrador") &&
            !userDTO.getPerfil().equalsIgnoreCase("Usuario")) {
            return ResponseEntity.badRequest().body("Perfil inválido. Use 'Administrador' ou 'Usuario'.");
        }

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Já existe um usuário com este e-mail.");
        }

        if (userRepository.findByCpf(userDTO.getCpf()).isPresent()) {
            return ResponseEntity.badRequest().body("Já existe um usuário com este CPF.");
        }

        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());
        user.setSenha(passwordEncoder.encode(userDTO.getSenha()));
        user.setCpf(userDTO.getCpf());
        user.setPerfil(userDTO.getPerfil());
        userRepository.save(user);

        return ResponseEntity.ok("Usuário cadastrado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = authService.login(loginDTO.getEmail(), loginDTO.getSenha());
            User user = authService.findUserByEmail(loginDTO.getEmail());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                "id", user.getId(),
                "nome", user.getNome(),
                "email", user.getEmail(),
                "perfil", user.getPerfil()
            ));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
