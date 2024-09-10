package br.com.fiap.api_gerenciamento_livros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fiap.api_gerenciamento_livros.exception.LivroNaoEncontradoException;
import br.com.fiap.api_gerenciamento_livros.model.Livro;

public class GerenciadorBiblioteca implements GerenciadorBibliotecaInterface {
    private List<Livro> livros;

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
        int idRemocao = this.livros.indexOf(getLivroPorISBN(isbn));
        livros.remove(idRemocao);
    }

    @Override
    public List<Livro> listarLivros(Optional<String> ordenacao, Optional<String> categoria) {
        List<Livro> livrosResult = getLivros();

        if(categoria.isPresent()) {

        }

        if(ordenacao.isPresent()) {

        }
        return livrosResult;
    }

    @Override
    public boolean reservarLivro(long isbn, long userID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reservarLivro'");
    }

    @Override
    public boolean cancelarReserva(long isbn, long userID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelarReserva'");
    }

    @Override
    public List<Long> listarReservas(long isbn) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarReservas'");
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
}
