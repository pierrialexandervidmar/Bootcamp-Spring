package com.devsuperior.dscatalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

/**
 * Serviço responsável pela lógica de negócios relacionada às categorias.
 * Fornece métodos para manipular categorias, incluindo operações CRUD.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    /**
     * Retorna uma página de categorias paginadas.
     *
     * @param pageRequest Objeto que contém informações de paginação e ordenação.
     * @return Uma página de DTOs de categoria.
     */
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
        Page<Category> list = repository.findAll(pageRequest);
        return list.map(x -> new CategoryDTO(x));
    }

    /**
     * Retorna uma categoria pelo seu ID.
     *
     * @param id O ID da categoria a ser retornada.
     * @return O DTO da categoria encontrada.
     * @throws ResourceNotFoundException Se a categoria não for encontrada.
     */
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
        return new CategoryDTO(entity);
    }
    
    /**
     * Insere uma nova categoria.
     *
     * @param obj O DTO da categoria a ser inserida.
     * @return O DTO da categoria inserida.
     */
    @Transactional
    public CategoryDTO insert(CategoryDTO obj) {
        Category entity = new Category();
        entity.setName(obj.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }
    
    /**
     * Atualiza uma categoria existente.
     *
     * @param id O ID da categoria a ser atualizada.
     * @param obj O DTO com os dados atualizados da categoria.
     * @return O DTO da categoria atualizada.
     * @throws ResourceNotFoundException Se a categoria não for encontrada.
     */
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO obj) {
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(obj.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado!");
        }
    }
    
    /**
     * Remove uma categoria pelo seu ID.
     *
     * @param id O ID da categoria a ser removida.
     * @throws ResourceNotFoundException Se a categoria não for encontrada.
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
}
