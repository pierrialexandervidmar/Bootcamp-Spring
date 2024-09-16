package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Objeto de Transferência de Dados (DTO) para a entidade User.
 * Esta classe é responsável por transferir dados do usuário entre diferentes camadas da aplicação.
 * Implementa {@link Serializable} para permitir que objetos dessa classe sejam serializados e desserializados.
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    Set<RoleDTO> roles = new HashSet<>();

    /**
     * Construtor padrão. Inicializa um objeto UserDTO vazio.
     */
    public UserDTO() {
    }

    /**
     * Construtor que inicializa o UserDTO com os campos especificados.
     * 
     * @param id         Identificador do usuário.
     * @param firstName  Primeiro nome do usuário.
     * @param lastName   Sobrenome do usuário.
     * @param email      Endereço de e-mail do usuário.
     */
    public UserDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Construtor que inicializa o UserDTO a partir de uma entidade User.
     * 
     * @param entity Entidade User utilizada para preencher os dados do DTO.
     */
    public UserDTO(User entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
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
     * @return Primeiro nome do usuário.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Define o primeiro nome do usuário.
     * 
     * @param firstName Primeiro nome do usuário.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retorna o sobrenome do usuário.
     * 
     * @return Sobrenome do usuário.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Define o sobrenome do usuário.
     * 
     * @param lastName Sobrenome do usuário.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retorna o endereço de e-mail do usuário.
     * 
     * @return Endereço de e-mail do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o endereço de e-mail do usuário.
     * 
     * @param email Endereço de e-mail do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o conjunto de roles associados ao usuário.
     * 
     * @return Conjunto de roles do usuário.
     */
    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
