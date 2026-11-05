package com.salvalivros.backend.controller;

import com.salvalivros.backend.dto.LivroRequest;
import com.salvalivros.backend.dto.LivroResponse;
import com.salvalivros.backend.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse criaLivro(@Valid @RequestBody LivroRequest livroRequest) {
        return livroService.salvarLivro(livroRequest);
    }

    @GetMapping()
    public List<LivroResponse> listaLivro() {
        return livroService.obterTodasOsLivros();
    }

    @GetMapping("/{id}")
    public LivroResponse buscaLivroPorId(@PathVariable Long id) {
        return livroService.obterLivroPorId(id);
    }

    @DeleteMapping("/{id}")
    public LivroResponse deletaLivroPorId(@PathVariable Long id) {
        return livroService.deletarLivro(id);
    }

    @PutMapping("/{id}")
    public LivroResponse atualizaLivroPorId(@PathVariable Long id, @Valid @RequestBody LivroRequest livroRequest) {
        return livroService.atualizarLivro(id, livroRequest);
    }
}
