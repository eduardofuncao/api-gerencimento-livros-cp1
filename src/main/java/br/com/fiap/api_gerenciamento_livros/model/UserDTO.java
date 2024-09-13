package br.com.fiap.api_gerenciamento_livros.model;

public class UserDTO {
    private long userId;

    public UserDTO() {
    }

    public UserDTO(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    
}
