package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;

import java.io.Serializable;
import java.util.Objects;

/**
 * Objeto de Transferência de Dados (DTO) para a entidade Category.
 * Esta classe é responsável por transferir dados de categoria entre diferentes camadas da aplicação.
 * Implementa {@link Serializable} para permitir a serialização e desserialização de objetos dessa classe.
 */
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    /**
     * Construtor padrão. Inicializa um objeto CategoryDTO vazio.
     */
    public CategoryDTO() {
    }

    /**
     * Construtor que inicializa o CategoryDTO a partir de uma entidade Category.
     * 
     * @param entity Entidade Category usada para preencher os dados do DTO.
     */
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    /**
     * Construtor que inicializa o CategoryDTO com os campos especificados.
     * 
     * @param id    Identificador da categoria.
     * @param name  Nome da categoria.
     */
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retorna o ID da categoria.
     * 
     * @return ID da categoria.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da categoria.
     * 
     * @param id ID da categoria.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome da categoria.
     * 
     * @return Nome da categoria.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da categoria.
     * 
     * @param name Nome da categoria.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Verifica se dois objetos CategoryDTO são iguais, comparando os campos id e name.
     * 
     * @param o Objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    /**
     * Gera o código hash para o objeto CategoryDTO com base nos campos id e name.
     * 
     * @return Código hash do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
