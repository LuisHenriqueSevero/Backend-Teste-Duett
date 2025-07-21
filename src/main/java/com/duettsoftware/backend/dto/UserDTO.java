package com.duettsoftware.backend.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String perfil;
}

