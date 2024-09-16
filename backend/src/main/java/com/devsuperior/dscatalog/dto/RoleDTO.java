package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Role;

import java.io.Serializable;

/**
 * Objeto de Transferência de Dados (DTO) para a entidade Role.
 * Esta classe é responsável por transferir dados de papéis/autoridades entre diferentes camadas da aplicação.
 * Implementa {@link Serializable} para permitir a serialização e desserialização de objetos dessa classe.
 */
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    /**
     * Construtor padrão. Inicializa um objeto RoleDTO vazio.
     */
    public RoleDTO() {
    }

    /**
     * Construtor que inicializa o RoleDTO com os campos especificados.
     * 
     * @param id        Identificador do papel.
     * @param authority Nome da autoridade associada ao papel.
     */
    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    /**
     * Construtor que inicializa o RoleDTO a partir de uma entidade Role.
     * 
     * @param role Entidade Role usada para preencher os dados do DTO.
     */
    public RoleDTO(Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }

    /**
     * Retorna o ID do papel.
     * 
     * @return ID do papel.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do papel.
     * 
     * @param id ID do papel.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna a autoridade associada ao papel.
     * 
     * @return Nome da autoridade.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Define a autoridade associada ao papel.
     * 
     * @param authority Nome da autoridade.
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
