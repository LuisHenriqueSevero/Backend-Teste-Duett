package com.duettsoftware.backend.controller;

import com.duettsoftware.backend.model.User;
import com.duettsoftware.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('Administrador')")
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @DeleteMapping("/usuarios/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok("Usuário excluído com sucesso");
        }).orElse(ResponseEntity.notFound().build());
    }
}
