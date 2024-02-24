package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
        return new ProductDTO(entity, entity.getCategories());
    }
    
    @Transactional
    public ProductDTO insert(ProductDTO obj) {
    	Product entity = new Product();
        copyDtoToEntity(obj, entity);
    	entity = repository.save(entity);
    	return new ProductDTO(entity);
    }

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

    private void copyDtoToEntity(ProductDTO obj, Product entity) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setDate(obj.getDate());
        entity.setImgUrl(obj.getImgUrl());
        entity.setPrice(obj.getPrice());

        entity.getCategories().clear();
        for(CategoryDTO catDto : obj.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            entity.getCategories().add(category);
        }
    }


}






















