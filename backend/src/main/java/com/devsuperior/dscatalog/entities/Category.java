package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

/**
 * Entidade que representa a categoria de produtos no sistema.
 * Implementa {@link Serializable} para que seus objetos possam ser serializados.
 * Cada categoria tem um conjunto de produtos associados e possui metadados sobre o momento de sua criação e última atualização.
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;
	
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<>();

	/**
	 * Construtor padrão. Inicializa um objeto Category vazio.
	 */
	public Category() {
	}

	/**
	 * Construtor que inicializa a categoria com ID e nome especificados.
	 * 
	 * @param id    Identificador da categoria.
	 * @param name  Nome da categoria.
	 */
	public Category(Long id, String name) {
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
	 * Retorna a data de criação da categoria.
	 * 
	 * @return Data de criação da categoria.
	 */
	public Instant getCreatedAt() {
		return createdAt;
	}

	/**
	 * Retorna a data da última atualização da categoria.
	 * 
	 * @return Data da última atualização da categoria.
	 */
	public Instant getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Método de callback executado automaticamente antes de persistir a entidade.
	 * Define o valor de {@code createdAt} como o momento atual.
	 */
	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}

	/**
	 * Método de callback executado automaticamente antes de atualizar a entidade.
	 * Define o valor de {@code updatedAt} como o momento atual.
	 */
	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

	/**
	 * Retorna o conjunto de produtos associados a esta categoria.
	 * 
	 * @return Conjunto de produtos associados.
	 */
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * Implementação de hashCode baseada no ID da categoria.
	 * 
	 * @return Código hash da categoria.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Verifica se duas categorias são iguais com base no ID.
	 * 
	 * @param obj Objeto a ser comparado.
	 * @return {@code true} se as categorias tiverem o mesmo ID; caso contrário, {@code false}.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
}
