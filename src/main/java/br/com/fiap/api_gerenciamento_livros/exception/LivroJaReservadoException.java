package br.com.fiap.api_gerenciamento_livros.exception;

public class LivroJaReservadoException extends RuntimeException {
    public LivroJaReservadoException(String message) {
        super(message);
    }
}
