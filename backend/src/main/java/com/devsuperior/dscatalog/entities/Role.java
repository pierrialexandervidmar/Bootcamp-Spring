package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entidade que representa o papel (role) de um usuário no sistema.
 * Implementa {@link Serializable} para permitir a serialização dos objetos da classe.
 * Cada papel pode estar associado a vários usuários.
 */
@Entity
@Table(name = "tb_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    /**
     * Construtor padrão. Inicializa um objeto Role vazio.
     */
    public Role() {
    }

    /**
     * Construtor que inicializa o papel com os parâmetros especificados.
     *
     * @param id        Identificador do papel.
     * @param authority Nome do papel (autoridade).
     */
    public Role(Long id, String authority) {
        super();
        this.id = id;
        this.authority = authority;
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
     * Retorna o nome da autoridade do papel.
     *
     * @return Nome da autoridade.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Define o nome da autoridade do papel.
     *
     * @param authority Nome da autoridade.
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * Retorna o conjunto de usuários associados a este papel.
     *
     * @return Conjunto de usuários.
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Implementação de hashCode baseada no ID do papel.
     *
     * @return Código hash do papel.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Verifica se dois papéis são iguais com base no ID.
     *
     * @param obj Objeto a ser comparado.
     * @return {@code true} se os papéis tiverem o mesmo ID; caso contrário, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Role other = (Role) obj;
        return Objects.equals(id, other.id);
    }
}
