package com.devsuperior.dscatalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

/**
 * Serviço responsável pela lógica de negócios relacionada aos produtos.
 * Fornece métodos para manipular produtos, incluindo operações CRUD.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retorna uma página de produtos paginados.
     *
     * @param pageable Objeto que contém informações de paginação e ordenação.
     * @return Uma página de DTOs de produto.
     */
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(x -> new ProductDTO(x));
    }

    /**
     * Retorna um produto pelo seu ID.
     *
     * @param id O ID do produto a ser retornado.
     * @return O DTO do produto encontrado, incluindo suas categorias.
     * @throws ResourceNotFoundException Se o produto não for encontrado.
     */
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
        return new ProductDTO(entity, entity.getCategories());
    }
    
    /**
     * Insere um novo produto.
     *
     * @param obj O DTO do produto a ser inserido.
     * @return O DTO do produto inserido.
     */
    @Transactional
    public ProductDTO insert(ProductDTO obj) {
        Product entity = new Product();
        copyDtoToEntity(obj, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    /**
     * Atualiza um produto existente.
     *
     * @param id O ID do produto a ser atualizado.
     * @param obj O DTO com os dados atualizados do produto.
     * @return O DTO do produto atualizado.
     * @throws ResourceNotFoundException Se o produto não for encontrado.
     */
    @Transactional
    public ProductDTO update(Long id, ProductDTO obj) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(obj, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado!");
        }
    }
    
    /**
     * Remove um produto pelo seu ID.
     *
     * @param id O ID do produto a ser removido.
     * @throws ResourceNotFoundException Se o produto não for encontrado.
     * @throws DatabaseException Se ocorrer uma violação de integridade referencial.
     */
    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado!");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    /**
     * Copia os dados do DTO para a entidade de produto.
     *
     * @param obj O DTO do produto com os dados a serem copiados.
     * @param entity A entidade de produto que receberá os dados do DTO.
     */
    private void copyDtoToEntity(ProductDTO obj, Product entity) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setDate(obj.getDate());
        entity.setImgUrl(obj.getImgUrl());
        entity.setPrice(obj.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDto : obj.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            entity.getCategories().add(category);
        }
    }
}
