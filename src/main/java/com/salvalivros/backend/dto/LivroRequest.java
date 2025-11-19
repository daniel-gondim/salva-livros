package com.salvalivros.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LivroRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    private String categoria;

    private String editora;

    private String isbn;
}
