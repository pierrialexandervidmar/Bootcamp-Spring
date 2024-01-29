package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
        return new CategoryDTO(entity);
    }
    
    @Transactional
    public CategoryDTO insert(CategoryDTO obj) {
    	Category entity = new Category();
    	entity.setName(obj.getName());
    	entity = repository.save(entity);
    	return new CategoryDTO(entity);
    }
    
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
    
    @Transactional
    public void delete(Long id) {	
    	try {
        	repository.deleteById(id);
		} 
    	catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado!");
		} 
    	catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation");
		}
    }
}






















