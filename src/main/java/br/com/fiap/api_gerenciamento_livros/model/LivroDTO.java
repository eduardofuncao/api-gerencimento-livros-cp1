package br.com.fiap.api_gerenciamento_livros.model;

import java.util.ArrayList;
import java.util.List;

public class LivroDTO {
    private String titulo;
    private String autor;
    private long isbn;
    private String categoria;
    private List<Long> reservas = new ArrayList<Long>();

    public LivroDTO() {
    }

    public LivroDTO(String titulo, String autor, long isbn, String categoria, List<Long> reservas) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.reservas = reservas;
    }

    public String getTitulo() {
        return titulo.toLowerCase();
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor.toLowerCase();
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public long getIsbn() {
        return isbn;
    }
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Long> getReservas() {
        return reservas;
    }

    public void setReservas(List<Long> reservas) {
        this.reservas = reservas;
    }

    
}
