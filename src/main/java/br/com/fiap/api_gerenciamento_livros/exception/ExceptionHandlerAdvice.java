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
        Map<String, String> response = Map.of("status", "400", "message", e.getMessage());
        return ResponseEntity.status(400).body(response);
    }   

    @ExceptionHandler(LivroJaReservadoException.class)
    public ResponseEntity<Map<String, String>> LivroJaReservasd(LivroJaReservadoException e) {
        Map<String, String> response = Map.of("status", "202", "message", e.getMessage());
        return ResponseEntity.status(400).body(response);
    }   

    /*
    @ExceptionHandler(TarefaJaConcluidaException.class)
    public ResponseEntity<Map<String, String>> handleTarefaConcluidaException(TarefaJaConcluidaException e) {
        Map<String, String> response = Map.of("status", "200", "message", e.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleTarefaNaoEncontradaException(TarefaNaoEncontradaException e) {
        Map<String, String> response = Map.of("status", "404", "message", e.getMessage());
        return ResponseEntity.status(404).body(response);
    }
*/
}