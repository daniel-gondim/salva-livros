package com.salvalivros.backend.dto;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class LivroResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    private String categoria;

    private String editora;

    private String isbn;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
