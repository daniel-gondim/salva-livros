package com.salvalivros.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "livro")
@Entity
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String titulo;

    @NotNull
    @Column(nullable = false)
    private String autor;

    @NotNull
    @Column(nullable = false)
    private String editora;

    @NotNull
    @Column(unique = true)
    private String isbn;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @NotNull
    @Column(nullable = false)
    private String categoria;
}
