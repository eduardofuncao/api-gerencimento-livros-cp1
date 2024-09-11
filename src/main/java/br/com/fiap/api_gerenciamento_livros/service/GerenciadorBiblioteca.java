package br.com.fiap.api_gerenciamento_livros.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.api_gerenciamento_livros.exception.LivroNaoEncontradoException;
import br.com.fiap.api_gerenciamento_livros.model.Livro;

public class GerenciadorBiblioteca implements GerenciadorBibliotecaInterface {
    private List<Livro> livros = new ArrayList<Livro>();

    @Override
    public Livro getLivroPorISBN(long isbn){
        Livro livroEncontrado = this.livros.stream()
            .filter(livro -> (livro.getIsbn() == isbn))
            .findFirst().orElseThrow(() -> new LivroNaoEncontradoException("Livro com isbn " + isbn + " não encontrado"));
        return livroEncontrado;
    }

    @Override
    public List<Livro> criarLivros(List<Livro> livrosCriados) {
        List<Livro> livrosAdicionados = new ArrayList<Livro>();
        for(Livro livro:livrosCriados) {
            livrosAdicionados.add(livro);
            this.livros.add(livro);
        }
        return livrosAdicionados;
    }

    @Override
    public void excluirLivro(long isbn) {
        livros.remove(getLivroPorISBN(isbn));
    }
/*
    @Override
    public List<Livro> listarLivros(Optional<String> ordenacao, Optional<String> categoria) {
        List<Livro> livrosResultado = getLivros();

        if(categoria.isPresent()) {
            livrosResultado.stream()
            .filter(livro -> (livro.getCategoria().equals(categoria.toString())))
            .collect(Collectors.toList());
        }

        if(ordenacao.isPresent()) {
            if (ordenacao.toString().equals("titulo")) {
                y
            } else if (ordenacao.toString().equals("autor")) {
                livrosResultado.stream()
                .sorted(Comparator.comparing(Livro::getAutor))
                .collect(Collectors.toList());
            }
            
        }
        return livrosResultado;
    }
*/
    @Override
    public List<Livro> listarLivros() {
        return getLivros();
    }

    @Override
    public List<Livro> listarLivrosOrdenadosPorPropriedade(String propriedade, List<Livro> livrosFiltrados) {
        if (propriedade.equals("autor")) {
            livrosFiltrados.stream()
                .sorted(Comparator.comparing(Livro::getAutor))
                .collect(Collectors.toList());
        } else if (propriedade.equals("titulo")) {
            livrosFiltrados.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .collect(Collectors.toList());
        }  
        return livrosFiltrados;
    }
    
    @Override 
    public List<Livro> listarLivrosFiltradosPorCategoria(String categoria) {
        List<Livro> livrosResultado = getLivros();
        livrosResultado.stream()
            .filter(livro -> (livro.getCategoria().equals(categoria)))
            .collect(Collectors.toList());
        return livrosResultado;
    }

    @Override
    public boolean reservarLivro(long isbn, long userID) {
        return getLivroPorISBN(isbn).fazReserva(userID);
    }

    @Override
    public boolean cancelarReserva(long isbn, long userID) {
        return getLivroPorISBN(isbn).cancelaReserva(userID);
    }

    @Override
    public List<Long> listarReservas(long isbn) {
        return getLivroPorISBN(isbn).getReservas();
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
}
