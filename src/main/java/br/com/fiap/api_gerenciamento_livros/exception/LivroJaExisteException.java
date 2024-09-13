package br.com.fiap.api_gerenciamento_livros.exception;

public class LivroJaExisteException extends RuntimeException {
    public LivroJaExisteException(String message) {
        super(message);
    }
}

