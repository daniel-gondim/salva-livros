package com.salvalivros.backend.controller;

import com.salvalivros.backend.dto.LivroResponse;
import com.salvalivros.backend.service.LivroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(LivroController.class)
class LivroControllerTest {
    @MockitoBean
    LivroService livroService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void deveCriarLivroQuandoDadosValidos() throws Exception {
        LivroResponse livroResponse = new LivroResponse();
        livroResponse.setAutor("Machado de Assis");
        livroResponse.setTitulo("Dom Casmurro");
        livroResponse.setCategoria("Romance");
        livroResponse.setEditora("Editora X");
        livroResponse.setIsbn("1234567890");

        when(livroService.salvarLivro(any())).thenReturn(livroResponse);
        mockMvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                          "titulo": "Dom Casmurro",
                          "autor": "Machado de Assis",
                          "categoria": "Romance",
                          "editora": "Editora X",
                          "isbn": "1234567890"
                        }"""))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Dom Casmurro"));
    }

    @Test
    void deveLancarExcecaoQuandoCampoAusente() throws Exception {
        mockMvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                          "titulo": "",
                          "autor": "Machado de Assis",
                          "categoria": "Romance",
                          "editora": "Editora X",
                          "isbn": "1234567890"
                        }"""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarLivroQuandoIdForValido() throws Exception {
        LivroResponse livroResponse = new LivroResponse();
        livroResponse.setAutor("Machado de Assis");
        livroResponse.setTitulo("Dom Casmurro");
        livroResponse.setCategoria("Romance");
        livroResponse.setEditora("Editora X");
        livroResponse.setIsbn("1234567890");

        when(livroService.obterLivroPorId(any())).thenReturn(livroResponse);
        mockMvc.perform(get("/livros/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.autor").value("Machado de Assis"));
    }

    @Test
    void deveLancarExcecaoQuandoIdForInexistente() throws Exception {
        when(livroService.obterLivroPorId(any())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        mockMvc.perform(get("/livros/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveAtualizarLivroQuandoDadosValidos() throws Exception{
        LivroResponse livroResponse = new LivroResponse();
        livroResponse.setAutor("Machado de Assis");
        livroResponse.setTitulo("Dom Casmurro");
        livroResponse.setCategoria("Romance");
        livroResponse.setEditora("Editora X");
        livroResponse.setIsbn("1234567890");
        when(livroService.atualizarLivro(any(), any())).thenReturn(livroResponse);
        mockMvc.perform(put("/livros/1").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                          "titulo": "Dom Casmurro",
                          "autor": "Machado de Assis",
                          "categoria": "Romance",
                          "editora": "Editora X",
                          "isbn": "1234567890"
                        }"""))
                .andExpect(status().isOk());
    }

    @Test
    void deveLancarExcecaoQuandoIdDeLivroAtualizadoInexistente() throws Exception{
        when(livroService.atualizarLivro(any(),any())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        mockMvc.perform(put("/livros/1").contentType(MediaType.APPLICATION_JSON).content("""
                        {
                          "titulo": "Dom Casmurro",
                          "autor": "Machado de Assis",
                          "categoria": "Romance",
                          "editora": "Editora X",
                          "isbn": "1234567890"
                        }"""))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveDeletarLivroExistente() throws Exception {
        LivroResponse livroResponse = new LivroResponse();
        livroResponse.setAutor("Machado de Assis");
        livroResponse.setTitulo("Dom Casmurro");
        livroResponse.setCategoria("Romance");
        livroResponse.setEditora("Editora X");
        livroResponse.setIsbn("1234567890");
        when(livroService.deletarLivro(any())).thenReturn(livroResponse);
        mockMvc.perform(delete("/livros/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveLancarExecaoQuandoLivroInexistente() throws Exception {
        when(livroService.deletarLivro(any())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        mockMvc.perform(delete("/livros/1"))
                .andExpect(status().isNotFound());
    }
}