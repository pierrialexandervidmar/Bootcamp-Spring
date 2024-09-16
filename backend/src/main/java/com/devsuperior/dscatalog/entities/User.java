package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

/**
 * Entidade que representa um usuário do sistema.
 * Implementa {@link Serializable} para permitir a serialização dos objetos da classe.
 * Cada usuário possui informações pessoais e um conjunto de papéis (roles) que definem suas permissões.
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", 
               joinColumns = @JoinColumn(name = "user_id"), 
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /**
     * Construtor padrão. Inicializa um objeto User vazio.
     */
    public User() {
    }

    /**
     * Construtor que inicializa o usuário com os parâmetros fornecidos.
     *
     * @param id        Identificador do usuário.
     * @param firstName Primeiro nome do usuário.
     * @param lastName  Sobrenome do usuário.
     * @param email     E-mail do usuário.
     * @param password  Senha do usuário.
     */
    public User(Long id, String firstName, String lastName, String email, String password) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Retorna o ID do usuário.
     *
     * @return ID do usuário.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do usuário.
     *
     * @param id ID do usuário.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o primeiro nome do usuário.
     *
     * @return Primeiro nome.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Define o primeiro nome do usuário.
     *
     * @param firstName Primeiro nome.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retorna o sobrenome do usuário.
     *
     * @return Sobrenome.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Define o sobrenome do usuário.
     *
     * @param lastName Sobrenome.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retorna o e-mail do usuário.
     *
     * @return E-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     *
     * @param email E-mail.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return Senha.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do usuário.
     *
     * @param password Senha.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retorna o conjunto de papéis (roles) atribuídos ao usuário.
     *
     * @return Conjunto de papéis.
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Implementação de hashCode baseada no ID do usuário.
     *
     * @return Código hash do usuário.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Verifica se dois usuários são iguais com base no ID.
     *
     * @param obj Objeto a ser comparado.
     * @return {@code true} se os usuários tiverem o mesmo ID; caso contrário, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }
}
