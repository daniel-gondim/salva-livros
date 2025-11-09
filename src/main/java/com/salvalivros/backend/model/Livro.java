package com.salvalivros.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Table(name = "livro")
@Entity
public class Livro {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    private String editora;

    private String isbn;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
