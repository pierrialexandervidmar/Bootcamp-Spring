package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Objeto de Transferência de Dados (DTO) para a entidade Product.
 * Esta classe é responsável por transferir dados do produto entre diferentes camadas da aplicação.
 * Implementa {@link Serializable} para permitir a serialização e desserialização de objetos dessa classe.
 */
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    @Size(min = 3, max = 100, message = "Deve ter entre 5 e 60 caracteres")
    private String name;
    
    @NotBlank(message = "Campo obrigatório")
    private String description;
    
    @Positive(message = "Preço deve ser um valor positivo")
    private Double price;
    private String imgUrl;
    
    @PastOrPresent(message = "A data do produto não pode ser futura")
    private Instant date;
    private List<CategoryDTO> categories = new ArrayList<>();

    /**
     * Construtor padrão. Inicializa um objeto ProductDTO vazio.
     */
    public ProductDTO() {
    }

    /**
     * Construtor que inicializa o ProductDTO com os campos especificados.
     * 
     * @param id          Identificador do produto.
     * @param name        Nome do produto.
     * @param description Descrição do produto.
     * @param price       Preço do produto.
     * @param imgUrl      URL da imagem do produto.
     * @param date        Data de criação do produto.
     */
    public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    /**
     * Construtor que inicializa o ProductDTO a partir de uma entidade Product.
     * 
     * @param entity Entidade Product usada para preencher os dados do DTO.
     */
    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.date = entity.getDate();
    }

    /**
     * Construtor que inicializa o ProductDTO a partir de uma entidade Product e um conjunto de categorias.
     * 
     * @param entity     Entidade Product usada para preencher os dados do DTO.
     * @param categories Conjunto de categorias associadas ao produto.
     */
    public ProductDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
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
     * Retorna a lista de categorias associadas ao produto.
     * 
     * @return Lista de categorias do produto.
     */
    public List<CategoryDTO> getCategories() {
        return categories;
    }

    /**
     * Define a lista de categorias associadas ao produto.
     * 
     * @param categories Lista de categorias do produto.
     */
    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }
}
