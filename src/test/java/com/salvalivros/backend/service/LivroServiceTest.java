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
    void deveObterTodosOsLivros() {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setAutor("Miguel de Cervantes");
        livroRequest.setTitulo("Dom Quixote");
        livroRequest.setEditora("Nova Fronteira");
        livroRequest.setCategoria("Clśsico");
        livroRequest.setIsbn("1234567890012");

        LivroRequest livroRequest2 = new LivroRequest();
        livroRequest2.setAutor("Os Miseráveis");
        livroRequest2.setTitulo("Victor Hugo");
        livroRequest2.setEditora("Globo");
        livroRequest2.setCategoria("Clśsico");
        livroRequest2.setIsbn("0246810121416");

        Livro livro = new Livro();
        livro.setAutor(livroRequest.getAutor());
        livro.setTitulo(livroRequest.getTitulo());
        livro.setCategoria(livroRequest.getCategoria());
        livro.setEditora(livroRequest.getEditora());
        livro.setIsbn(livroRequest.getIsbn());

        Livro livro2 = new Livro();
        livro2.setAutor(livroRequest2.getAutor());
        livro2.setTitulo(livroRequest2.getTitulo());
        livro2.setCategoria(livroRequest2.getCategoria());
        livro2.setEditora(livroRequest2.getEditora());
        livro2.setIsbn(livroRequest2.getIsbn());

        List<Livro> listaLivros = new ArrayList<>();
        listaLivros.add(livro);
        listaLivros.add(livro2);
        when(livroRepository.findAll()).thenReturn(listaLivros);

        List<LivroResponse> listaLivrosResponse = livroService.obterTodasOsLivros();

        assertEquals(2, listaLivrosResponse.size());
        assertEquals(listaLivrosResponse.get(0).getTitulo(), livro.getTitulo());
        assertEquals(listaLivrosResponse.get(1).getTitulo(), livro2.getTitulo());
    }

    @Test
    void obterLivroPorId() {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setAutor("O médico e o monstro");
        livroRequest.setTitulo("O médico e o monstro");
        livroRequest.setEditora("Editora 34");
        livroRequest.setCategoria("Clśsico");
        livroRequest.setIsbn("1145678900129");

        Livro livro = new Livro();
        livro.setAutor(livroRequest.getAutor());
        livro.setTitulo(livroRequest.getTitulo());
        livro.setCategoria(livroRequest.getCategoria());
        livro.setEditora(livroRequest.getEditora());
        livro.setIsbn(livroRequest.getIsbn());
        Optional<Livro> livroEncontrado = Optional.of(livro);
        when(livroRepository.findById(any())).thenReturn(livroEncontrado);

        LivroResponse livroResponse = livroService.obterLivroPorId(50L);
        assertEquals(livroResponse.getTitulo(), livroRequest.getTitulo());
        assertEquals(livroResponse.getAutor(), livroRequest.getAutor());
        assertEquals(livroResponse.getCategoria(), livroRequest.getCategoria());
        assertEquals(livroResponse.getEditora(), livroRequest.getEditora());
        assertEquals(livroResponse.getIsbn(), livroRequest.getIsbn());
    }

    @Test
    void deletarLivro() {
    }

    @Test
    void atualizarLivro() {
    }
}