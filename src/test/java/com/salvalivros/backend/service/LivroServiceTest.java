package com.salvalivros.backend.service;

import com.salvalivros.backend.dto.LivroRequest;
import com.salvalivros.backend.dto.LivroResponse;
import com.salvalivros.backend.model.Livro;
import com.salvalivros.backend.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class LivroServiceTest {

    @Mock
    @ExtendWith(MockitoExtension.class)
    LivroRepository livroRepository;

    @InjectMocks
    LivroService livroService;

    @Test
    void deveCriarLivroQuandoDadosValidos() {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setAutor("Miguel de Cervantes");
        livroRequest.setTitulo("Dom Quixote");
        livroRequest.setEditora("Nova Fronteira");
        livroRequest.setCategoria("Clśsico");
        livroRequest.setIsbn("1234567890012");

        Livro livro = new Livro();
        livro.setAutor(livroRequest.getAutor());
        livro.setTitulo(livroRequest.getTitulo());
        livro.setCategoria(livroRequest.getCategoria());
        livro.setEditora(livroRequest.getEditora());
        livro.setIsbn(livroRequest.getIsbn());
        when(livroRepository.save(any())).thenReturn(livro);

        LivroResponse livroResponse = livroService.salvarLivro(livroRequest);

        assertEquals(livroResponse.getTitulo(), livroRequest.getTitulo());
        assertEquals(livroResponse.getAutor(), livroRequest.getAutor());
        assertEquals(livroResponse.getCategoria(), livroRequest.getCategoria());
        assertEquals(livroResponse.getEditora(), livroRequest.getEditora());
        assertEquals(livroResponse.getIsbn(), livroRequest.getIsbn());
    }

    @Test
    void obterTodasOsLivros() {
    }

    @Test
    void obterLivroPorId() {
    }

    @Test
    void deletarLivro() {
    }

    @Test
    void atualizarLivro() {
    }
}