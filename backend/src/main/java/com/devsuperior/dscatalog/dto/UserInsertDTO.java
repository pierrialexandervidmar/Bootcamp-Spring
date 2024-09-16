package com.devsuperior.dscatalog.dto;

/**
 * Classe de transferência de dados (DTO) usada para inserir novos usuários.
 * Herda os campos de {@link UserDTO} e adiciona o campo de senha.
 * É utilizada nas operações de criação de novos usuários, onde a senha também precisa ser enviada.
 */
public class UserInsertDTO extends UserDTO {
    private static final long serialVersionUID = 1L;

    // Campo que armazena a senha do novo usuário
    private String password;

    /**
     * Construtor padrão. Chama o construtor da superclasse {@link UserDTO}.
     */
    public UserInsertDTO() {
        super();
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário.
     *
     * @param password A nova senha do usuário.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
