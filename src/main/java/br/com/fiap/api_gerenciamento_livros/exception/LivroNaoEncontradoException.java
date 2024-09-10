package br.com.fiap.api_gerenciamento_livros.exception;

public class LivroNaoEncontradoException extends RuntimeException{
    public LivroNaoEncontradoException(String message) {
        super(message);
    }
}
