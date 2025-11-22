package com.salvalivros.backend.service;

import com.salvalivros.backend.model.Livro;
import com.salvalivros.backend.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

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

    public void deletaLivro(Long id) {
        this.livroRepository.deleteById(id);
    }

}
