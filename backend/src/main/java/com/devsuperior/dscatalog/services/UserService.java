package com.devsuperior.dscatalog.services;

import java.util.Optional;

import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.RoleDTO;
import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.entities.Role;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.RoleRepository;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

/**
 * Serviço responsável pela lógica de negócios relacionada aos usuários.
 * Oferece operações de criação, leitura, atualização e exclusão (CRUD),
 * além de validações e manipulações relacionadas a entidades de usuário.
 */
@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Retorna uma lista paginada de usuários com base nos parâmetros de paginação fornecidos.
     *
     * @param pageable Objeto que contém informações de paginação e ordenação.
     * @return Uma página de {@link UserDTO} contendo os usuários.
     */
    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser buscado.
     * @return Um {@link UserDTO} representando o usuário encontrado.
     * @throws ResourceNotFoundException Se o usuário com o ID fornecido não for encontrado.
     */
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada!"));
        return new UserDTO(entity);
    }

    /**
     * Insere um novo usuário no sistema.
     * O DTO de inserção inclui uma senha que será criptografada antes de ser salva.
     *
     * @param obj O {@link UserInsertDTO} com as informações do novo usuário.
     * @return Um {@link UserDTO} representando o usuário recém-criado.
     */
    @Transactional
    public UserDTO insert(UserInsertDTO obj) {
        User entity = new User();
        copyDtoToEntity(obj, entity);
        entity.setPassword(passwordEncoder.encode(obj.getPassword()));
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param id O ID do usuário a ser atualizado.
     * @param obj O {@link UserDTO} contendo os novos dados do usuário.
     * @return O {@link UserDTO} representando o usuário atualizado.
     * @throws ResourceNotFoundException Se o usuário com o ID fornecido não for encontrado.
     */
    @Transactional
    public UserDTO update(Long id, UserUpdateDTO obj) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(obj, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id " + id + " não encontrado!");
        }
    }

    /**
     * Exclui um usuário do sistema pelo seu ID.
     *
     * @param id O ID do usuário a ser removido.
     * @throws ResourceNotFoundException Se o usuário com o ID fornecido não for encontrado.
     * @throws DatabaseException Se houver uma violação de integridade referencial durante a exclusão.
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
     * Copia os dados de um {@link UserDTO} para uma entidade {@link User}.
     *
     * @param obj O DTO contendo os dados do usuário.
     * @param entity A entidade de usuário que receberá os dados copiados.
     */
    private void copyDtoToEntity(UserDTO obj, User entity) {
        entity.setFirstName(obj.getFirstName());
        entity.setLastName(obj.getLastName());
        entity.setEmail(obj.getEmail());

        entity.getRoles().clear();
        for (RoleDTO roleDto : obj.getRoles()) {
            Role role = roleRepository.getOne(roleDto.getId());
            entity.getRoles().add(role);
        }
    }
}
