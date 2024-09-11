package br.com.fiap.api_gerenciamento_livros.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.api_gerenciamento_livros.model.Livro;
import br.com.fiap.api_gerenciamento_livros.service.GerenciadorBiblioteca;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {
    GerenciadorBiblioteca gerenciadorBiblioteca = new GerenciadorBiblioteca();

     
    @PostMapping
    public ResponseEntity<List<Livro>> criarLivros(@RequestBody List<Livro> livros) {
        return ResponseEntity.status(201).body(gerenciadorBiblioteca.criarLivros(livros));
    }
    

    // TODO Criar map no gerenciador que coloca livros na categoria correta no momento da criação
    @GetMapping
    public ResponseEntity<List<Livro>> listarLivrosPorCategoriaEOrdenados( 
            @RequestParam(value="ordenarPor", required = false) String ordenarPor,
            @RequestParam(value="categoria", required = false) String categoria) {
        List<Livro> livrosFiltradosOrdenados = gerenciadorBiblioteca.listarLivros();
        System.out.println(categoria);
        System.out.println(ordenarPor);
        
        
        if (categoria != null) {
            System.out.println("aqui");
            return ResponseEntity.ok(gerenciadorBiblioteca.listarLivrosFiltradosPorCategoria(categoria));
        }
        if (ordenarPor != null) {
           return ResponseEntity.ok(gerenciadorBiblioteca.listarLivrosOrdenadosPorPropriedade(ordenarPor, livrosFiltradosOrdenados)); 
        }

        return ResponseEntity.ok(livrosFiltradosOrdenados);
    }
}
