package com.salvalivros.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salvalivros.backend.model.Livro;
import com.salvalivros.backend.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    private Livro salvarLivro(Livro livro) {    
        return livroRepository.save(livro); 
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
