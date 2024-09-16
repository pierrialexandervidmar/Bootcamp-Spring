package com.devsuperior.dscatalog.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade que representa o produto no sistema.
 * Implementa {@link Serializable} para permitir a serialização dos objetos da classe.
 * Cada produto possui um conjunto de categorias associadas e contém dados como nome, descrição, preço, URL da imagem e data de criação.
 */
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
               joinColumns = @JoinColumn(name = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();

    /**
     * Construtor padrão. Inicializa um objeto Product vazio.
     */
    public Product() {
    }

    /**
     * Construtor que inicializa o produto com os parâmetros especificados.
     * 
     * @param id          Identificador do produto.
     * @param name        Nome do produto.
     * @param description Descrição detalhada do produto.
     * @param price       Preço do produto.
     * @param imgUrl      URL da imagem do produto.
     * @param date        Data de criação do produto.
     */
    public Product(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    /**
     * Retorna o ID do produto.
     * 
     * @return ID do produto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do produto.
     * 
     * @param id ID do produto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     * 
     * @return Nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do produto.
     * 
     * @param name Nome do produto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna a descrição do produto.
     * 
     * @return Descrição do produto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do produto.
     * 
     * @param description Descrição do produto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retorna o preço do produto.
     * 
     * @return Preço do produto.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Define o preço do produto.
     * 
     * @param price Preço do produto.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Retorna a URL da imagem do produto.
     * 
     * @return URL da imagem do produto.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Define a URL da imagem do produto.
     * 
     * @param imgUrl URL da imagem do produto.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Retorna a data de criação do produto.
     * 
     * @return Data de criação do produto.
     */
    public Instant getDate() {
        return date;
    }

    /**
     * Define a data de criação do produto.
     * 
     * @param date Data de criação do produto.
     */
    public void setDate(Instant date) {
        this.date = date;
    }

    /**
     * Retorna o conjunto de categorias associadas ao produto.
     * 
     * @return Conjunto de categorias do produto.
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Verifica se dois produtos são iguais com base no ID.
     * 
     * @param o Objeto a ser comparado.
     * @return {@code true} se os produtos tiverem o mesmo ID; caso contrário, {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    /**
     * Implementação de hashCode baseada no ID do produto.
     * 
     * @return Código hash do produto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
