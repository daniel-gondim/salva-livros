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
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class LivroServiceTest {

    @Mock
    @ExtendWith(MockitoExtension.class)
    LivroRepository livroRepository;

    @InjectMocks
    LivroService livroService;

    private static final String AUTOR_PADRAO = "Autor";
    private static final String TITULO_PADRAO = "Título Teste";

    private Livro criaLivro() {
        Livro livro = new Livro();
        livro.setAutor(AUTOR_PADRAO);
        livro.setCategoria("Categoria Teste");
        livro.setTitulo(TITULO_PADRAO);
        livro.setEditora("Editora Teste");
        livro.setIsbn("1234567890123");
        return livro;
    }

    private LivroRequest criaLivroRequest() {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setAutor(AUTOR_PADRAO);
        livroRequest.setCategoria("Categoria Teste");
        livroRequest.setTitulo(TITULO_PADRAO);
        livroRequest.setEditora("Editora Teste");
        livroRequest.setIsbn("1234567890123");
        return livroRequest;
    }


    @Test
    void deveCriarLivroQuandoDadosValidos() {
        LivroRequest livroRequest = criaLivroRequest();
        Livro livro = criaLivro();
        when(livroRepository.save(any())).thenReturn(livro);
        LivroResponse livroResponse = livroService.salvarLivro(livroRequest);

        assertEquals(livroResponse.getTitulo(), livroRequest.getTitulo());
        assertEquals(livroResponse.getAutor(), livroRequest.getAutor());
        assertEquals(livroResponse.getCategoria(), livroRequest.getCategoria());
        assertEquals(livroResponse.getEditora(), livroRequest.getEditora());
        assertEquals(livroResponse.getIsbn(), livroRequest.getIsbn());
    }

    @Test
    void deveObterTodosOsLivros() {
        Livro livro = criaLivro();
        Livro livro2 = criaLivro();
        List<Livro> listaLivros = new ArrayList<>();
        listaLivros.add(livro);
        listaLivros.add(livro2);
        when(livroRepository.findAll()).thenReturn(listaLivros);
        List<LivroResponse> listaLivrosResponse = livroService.obterTodasOsLivros();

        assertEquals(2, listaLivrosResponse.size());
        assertEquals(listaLivrosResponse.get(0).getTitulo(), livro.getTitulo());
        assertEquals(listaLivrosResponse.get(1).getTitulo(), livro2.getTitulo());
        assertEquals(listaLivrosResponse.get(0).getIsbn(), livro.getIsbn());
        assertEquals(listaLivrosResponse.get(1).getIsbn(), livro2.getIsbn());
    }

    @Test
    void deveObterLivroPorId() {
        LivroRequest livroRequest = criaLivroRequest();
        Livro livro = criaLivro();
        Optional<Livro> livroEncontrado = Optional.of(livro);
        when(livroRepository.findById(any())).thenReturn(livroEncontrado);
        LivroResponse livroResponse = livroService.obterLivroPorId(50L);

        assertEquals(livroResponse.getTitulo(), livroRequest.getTitulo());
        assertEquals(livroResponse.getAutor(), livroRequest.getAutor());
        assertEquals(livroResponse.getCategoria(), livroRequest.getCategoria());
        assertEquals(livroResponse.getEditora(), livroRequest.getEditora());
    }

    @Test
    void deveRetornarMensagemDeLivroNaoEncontrado() {
        Optional<Livro> livroEncontrado = Optional.empty();
        when(livroRepository.findById(any())).thenReturn(livroEncontrado);
        assertThrows(ResponseStatusException.class, () -> livroService.obterLivroPorId(50L), "Livro não encontrado");
    }

    @Test
    void deveDeletarLivro() {
        LivroRequest livroRequest = criaLivroRequest();
        Livro livro = criaLivro();
        Optional<Livro> livroEncontrado = Optional.of(livro);

        when(livroRepository.findById(any())).thenReturn(livroEncontrado);
        LivroResponse livroResponse = livroService.deletarLivro(50L);
        assertEquals(livroResponse.getTitulo(), livroRequest.getTitulo());
    }

    @Test
    void deveAtualizarLivro() {
        Livro livro = criaLivro();
        Optional<Livro> livroEncontrado = Optional.of(livro);

        when(livroRepository.findById(any())).thenReturn(livroEncontrado);
        LivroRequest livroRequestAtualizado = criaLivroRequest();
        Livro livroAtualizado = criaLivro();
        when(livroRepository.save(any())).thenReturn(livroAtualizado);

        LivroResponse livroResponseAtualizado = livroService.atualizarLivro(80L, livroRequestAtualizado);
        assertEquals(livroResponseAtualizado.getTitulo(), livroRequestAtualizado.getTitulo());
        assertEquals(livroResponseAtualizado.getAutor(), livroRequestAtualizado.getAutor());
        assertEquals(livroResponseAtualizado.getCategoria(), livroRequestAtualizado.getCategoria());
        assertEquals(livroResponseAtualizado.getEditora(), livroRequestAtualizado.getEditora());
    }
}