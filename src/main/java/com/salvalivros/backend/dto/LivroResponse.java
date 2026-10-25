package com.salvalivros.backend.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
