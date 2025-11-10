package com.salvalivros.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "livro")
@Entity
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    private String editora;

    private String isbn;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String categoria;
}
