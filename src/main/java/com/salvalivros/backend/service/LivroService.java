package com.salvalivros.backend.service;

import java.util.List;

import com.salvalivros.backend.dto.LivroRequest;
import com.salvalivros.backend.dto.LivroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salvalivros.backend.model.Livro;
import com.salvalivros.backend.repository.LivroRepository;

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

    public List<Livro> obterTodasOsLivros() {
        return livroRepository.findAll();
    }

    public Livro obterLivroPorId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public void deletarLivro(Long id) {
        this.livroRepository.deleteById(id);
    }

}
