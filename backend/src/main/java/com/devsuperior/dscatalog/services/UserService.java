package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
        return new UserDTO(entity);
    }
    
    @Transactional
    public UserDTO insert(UserDTO obj) {
    	User entity = new User();
        copyDtoToEntity(obj, entity);
    	entity = repository.save(entity);
    	return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO obj) {	
    	try {
        	User entity = repository.getReferenceById(id);
            copyDtoToEntity(obj, entity);
        	entity = repository.save(entity);
        	return new UserDTO(entity);
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

    private void copyDtoToEntity(UserDTO obj, User entity) {
        entity.setFirstName(obj.getFirstName());
        entity.setLastName(obj.getLastName());
        entity.setEmail(obj.getEmail());

        entity.getCategories().clear();
        for(CategoryDTO catDto : obj.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            entity.getCategories().add(category);
        }
    }


}






















