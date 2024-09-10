package br.com.fiap.api_gerenciamento_livros.model;

public interface LivroInterface {
    boolean isReservado();
    boolean fazReserva(long UserID);
    long entregaLivro();
    boolean cancelaReserva(long userID); 
}
