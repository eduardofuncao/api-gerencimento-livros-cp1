package br.com.fiap.api_gerenciamento_livros.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> LivroNaoEncontrado(LivroNaoEncontradoException e) {
        Map<String, String> response = Map.of("status", "404", "message", e.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(LivroJaExisteException.class)
    public ResponseEntity<Map<String, String>> LivroJaExiste(LivroJaExisteException e) {
        Map<String, String> response = Map.of("status", "409", "message", e.getMessage());
        return ResponseEntity.status(409).body(response);
    }   

    @ExceptionHandler(LivroJaReservadoException.class)
    public ResponseEntity<Map<String, String>> LivroJaReservasd(LivroJaReservadoException e) {
        Map<String, String> response = Map.of("status", "200", "message", e.getMessage());
        return ResponseEntity.status(200).body(response);
    }   
}