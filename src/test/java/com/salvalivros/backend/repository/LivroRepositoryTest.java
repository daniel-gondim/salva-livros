package com.salvalivros.backend.repository;

import com.salvalivros.backend.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Test
    void deveSalvarLivro() {
        Livro livro = new Livro();
        livro.setAutor("Autor");
        livro.setCategoria("Categoria Teste");
        livro.setTitulo("Autor Teste");
        livro.setEditora("Editora Teste");
        livro.setIsbn("12313132121231");
        livroRepository.save(livro);
        assert livro.getId() != null;
    }
}