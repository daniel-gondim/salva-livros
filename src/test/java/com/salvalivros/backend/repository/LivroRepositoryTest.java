package com.salvalivros.backend.repository;

import com.salvalivros.backend.model.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @BeforeEach
    void limpaBanco() {
        livroRepository.deleteAll();
    }

    private Livro criaLivro() {
        Livro livro = new Livro();
        livro.setAutor("Autor");
        livro.setCategoria("Categoria Teste");
        livro.setTitulo("Título Teste");
        livro.setEditora("Editora Teste");
        livro.setIsbn(geraIsbnUnico());
        return livro;
    }

    private String geraIsbnUnico() {
        return UUID.randomUUID().toString();
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
        assertEquals("Título Teste", encontrado.getTitulo());
    }

    @Test
    void deveListarTodosOsLivros() {
        livroRepository.deleteAll();
        Livro livro1 = criaLivro();
        Livro livro2 = criaLivro();
        Livro livro3 = criaLivro();
        livroRepository.save(livro1);
        livroRepository.save(livro2);
        livroRepository.save(livro3);
        assertEquals(3, livroRepository.count());
        List<Livro> listaLivros = livroRepository.findAll();
        assertEquals("Autor", listaLivros.get(0).getAutor());
        assertEquals("Categoria Teste", listaLivros.get(1).getCategoria());
        assertEquals("Título Teste", listaLivros.get(2).getTitulo());
    }

    @Test
    void deveAtualizarLivro() {
        Livro livro = criaLivro();
        livro.setTitulo("Autor Alterado");
        assertEquals("Autor Alterado", livro.getTitulo());
    }

    @Test
    void deveDeletarLivro() {
        Livro livro = criaLivro();
        livroRepository.save(livro);
        livroRepository.delete(livro);
        assertEquals(0, livroRepository.count());
        Optional<Livro> livroEncontrado = livroRepository.findById(livro.getId());
        assertTrue(livroEncontrado.isEmpty());
    }
}
