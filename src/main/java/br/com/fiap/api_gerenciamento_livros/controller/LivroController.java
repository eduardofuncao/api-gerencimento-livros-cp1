package br.com.fiap.api_gerenciamento_livros.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.api_gerenciamento_livros.model.LivroDTO;
import br.com.fiap.api_gerenciamento_livros.model.UserDTO;
import br.com.fiap.api_gerenciamento_livros.service.GerenciadorBiblioteca;

@RestController
@RequestMapping("/api/v1")
public class LivroController {
    GerenciadorBiblioteca gerenciadorBiblioteca = new GerenciadorBiblioteca();

     
    @PostMapping("/livros")
    public ResponseEntity<List<LivroDTO>> criarLivros(@RequestBody List<LivroDTO> livrosDTO) {
        return ResponseEntity.status(201).body(gerenciadorBiblioteca.criarLivros(livrosDTO));
    }
    

    /*
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
        */
    

    // TODO fazer funcionar para uso de categoria e ordernarPor simultaneamente
    @GetMapping("/livros")
    public ResponseEntity<List<LivroDTO>> listarPorCategoria(
            @RequestParam(value="categoria", required=false) String categoria,
            @RequestParam(value="ordenarPor", required=false) String ordenarPor) {
        
        if(gerenciadorBiblioteca.getLivros().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum livro adicionado na biblioteca");
        }

        if (categoria != null)
            return ResponseEntity.ok(gerenciadorBiblioteca.listarLivrosFiltradosPorCategoria(categoria));
        if (ordenarPor != null)
            return ResponseEntity.ok(gerenciadorBiblioteca.listarLivrosOrdenadosPorPropriedade(ordenarPor));
        return ResponseEntity.ok(gerenciadorBiblioteca.listarLivros());
    }

    @DeleteMapping("/livros/{isbn}")    
    public ResponseEntity<Void> excluirLivro(@PathVariable("isbn") long isbn) {
        gerenciadorBiblioteca.excluirLivro(isbn);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reservas/{isbn}")
    public ResponseEntity<Boolean> criarReservas(
            @PathVariable("isbn") long isbn,
            @RequestBody UserDTO userDTO) {
        
        return ResponseEntity.status(201).body(gerenciadorBiblioteca.reservarLivro(isbn, userDTO.getUserId()));
    }

    @GetMapping("/reservas/{isbn}")
    public ResponseEntity<List<Long>> listarReservas(@PathVariable("isbn") long isbn) {
        return ResponseEntity.ok(gerenciadorBiblioteca.listarReservas(isbn));
    }
    
    @DeleteMapping("/reservas/{isbn}/{userId}")
    public ResponseEntity<Void> cancelarReserva(
            @PathVariable("isbn") long isbn,
            @PathVariable("userId") long userId) {
        gerenciadorBiblioteca.cancelarReserva(isbn, userId);
        return ResponseEntity.noContent().build();
    } 

    //TODO implementar endpoints de entrega e devolução do livro corretamente
    @PatchMapping("/reservas/{isbn}/devolver")
    public ResponseEntity<Long> devolveLivro(@PathVariable("isbn") long isbn) {
        return ResponseEntity.ok(gerenciadorBiblioteca.getLivroPorISBN(isbn).entregaLivro());
    }

}
