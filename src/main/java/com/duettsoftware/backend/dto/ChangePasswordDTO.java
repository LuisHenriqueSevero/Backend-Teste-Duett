package com.duettsoftware.backend.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String senhaAntiga;
    private String novaSenha;
    private String confirmacaoNovaSenha;
}
