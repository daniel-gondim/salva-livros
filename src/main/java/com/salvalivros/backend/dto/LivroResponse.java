package com.salvalivros.backend.dto;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class LivroResponse {

    private Long id;

    private String titulo;

    private String autor;

    private String categoria;

    private String editora;

    private String isbn;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
