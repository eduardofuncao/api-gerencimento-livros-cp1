package br.com.fiap.api_gerenciamento_livros.model;

public class User {
    private long userId;

    public User() {
    }

    public User(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    
}
