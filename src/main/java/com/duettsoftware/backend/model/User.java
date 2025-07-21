package com.duettsoftware.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    private String senha;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String perfil; //Usuario ou Administrador
}