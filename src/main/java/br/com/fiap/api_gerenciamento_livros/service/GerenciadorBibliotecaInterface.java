package br.com.fiap.api_gerenciamento_livros.service;

import java.util.List;

import br.com.fiap.api_gerenciamento_livros.model.Livro;

public interface GerenciadorBibliotecaInterface {
    //Classe responsável por toda a lógica de negócios da aplicação. - 
    //Métodos para criar livros, listar livros, excluir livros, reservar livros, 
    //listar a fila de espera e cancelar reservas.

    Livro getLivroPorISBN(long isbn);
    List<Livro> criarLivros(List<Livro> livros);
    void excluirLivro(long isbn);

    List<Livro> listarLivros();
    List<Livro> listarLivrosOrdenadosPorPropriedade(String propriedade);
    List<Livro> listarLivrosFiltradosPorCategoria(String categoria);
    boolean reservarLivro(long isbn, long userID);
    boolean cancelarReserva(long isbn, long userID);
    List<Long> listarReservas(long isbn);
}
