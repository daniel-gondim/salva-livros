package com.salvalivros.backend.repository;

import com.salvalivros.backend.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    private Livro criaLivro() {
        Livro livro = new Livro();
        livro.setAutor("Autor");
        livro.setCategoria("Categoria Teste");
        livro.setTitulo("Autor Teste");
        livro.setEditora("Editora Teste");
        livro.setIsbn("12313132121231");
        return livro;
    }

    @Test
    void deveSalvarLivro() {
        Livro livro = criaLivro();
        livroRepository.save(livro);
        assert livro.getId() != null;
    }

    @Test
    void deveBuscarLivroPorId() {
        Livro livro = criaLivro();
        livroRepository.save(livro);
        Optional<Livro> livroEncontrado = livroRepository.findById(livro.getId());
        assert livroEncontrado.isPresent();

        Livro encontrado = livroEncontrado.get();
        assertEquals("Autor Teste", encontrado.getTitulo());
    }
}