package com.salvalivros.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.salvalivros.backend.dto.LivroRequest;
import com.salvalivros.backend.dto.LivroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.salvalivros.backend.model.Livro;
import com.salvalivros.backend.repository.LivroRepository;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public LivroResponse salvarLivro(LivroRequest livroRequest) {
        Livro livro = new Livro();
        livro.setTitulo(livroRequest.getTitulo());
        livro.setAutor(livroRequest.getAutor());
        livro.setCategoria(livroRequest.getCategoria());
        livro.setEditora(livroRequest.getEditora());
        livro.setIsbn(livroRequest.getIsbn());
        Livro livroSalvo = livroRepository.save(livro);
        LivroResponse livroResponse = criaLivroResponse(livroSalvo);
        return livroResponse;
    }

    @NonNull
    private static LivroResponse criaLivroResponse(Livro livroSalvo) {
        LivroResponse livroResponse = new LivroResponse();
        livroResponse.setTitulo(livroSalvo.getTitulo());
        livroResponse.setAutor(livroSalvo.getAutor());
        livroResponse.setCategoria(livroSalvo.getCategoria());
        livroResponse.setEditora(livroSalvo.getEditora());
        livroResponse.setIsbn(livroSalvo.getIsbn());
        livroResponse.setCreatedAt(livroSalvo.getCreatedAt());
        livroResponse.setUpdatedAt(livroSalvo.getUpdatedAt());
        livroResponse.setId(livroSalvo.getId());
        return livroResponse;
    }

    public List<LivroResponse> obterTodasOsLivros() {
        List<Livro> listaLivros = livroRepository.findAll();
        List<LivroResponse> listaResponse = new ArrayList<>();
        for (int i = 0; i < listaLivros.size(); i++) {
            Livro livro = listaLivros.get(i);
            LivroResponse livroResponse = criaLivroResponse(livro);
            listaResponse.add(livroResponse);
        }
        return listaResponse;
    }

    public LivroResponse obterLivroPorId(Long id) {
        Optional<Livro> livroEncontrado = livroRepository.findById(id);
        if(livroEncontrado.isPresent()) {
            Livro livro = livroEncontrado.get();
            LivroResponse livroResponse = criaLivroResponse(livro);
            return livroResponse;
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrato");
    }

    public void deletarLivro(Long id) {
        this.livroRepository.deleteById(id);
    }

}
