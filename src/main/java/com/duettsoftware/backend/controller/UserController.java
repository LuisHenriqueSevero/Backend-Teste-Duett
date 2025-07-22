package com.duettsoftware.backend.controller;

import com.duettsoftware.backend.dto.ChangePasswordDTO;
import com.duettsoftware.backend.model.User;
import com.duettsoftware.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/alterarsenha")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!passwordEncoder.matches(dto.getSenhaAntiga(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Senha antiga incorreta");
        }

        if (!dto.getNovaSenha().equals(dto.getConfirmacaoNovaSenha())) {
            return ResponseEntity.badRequest().body("Nova senha e confirmação não coincidem");
        }

        user.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        userRepository.save(user);

        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}
