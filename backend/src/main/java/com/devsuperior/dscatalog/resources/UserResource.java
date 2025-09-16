package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import com.devsuperior.dscatalog.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


/**
 * Controlador REST para recursos de usuários.
 * Mapeia endpoints para operações CRUD (criação, leitura, atualização e remoção) de usuários.
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    /**
     * Endpoint para buscar todos os usuários paginados.
     *
     * @param pageable Configurações de paginação e ordenação.
     * @return Resposta HTTP com a página de usuários.
     */
    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para buscar um usuário específico pelo ID.
     *
     * @param id ID do usuário.
     * @return Resposta HTTP com os dados do usuário.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO item = service.findById(id);
        return ResponseEntity.ok().body(item);
    }

    /**
     * Endpoint para atualizar um usuário existente.
     *
     * @param id  ID do usuário a ser atualizado.
     * @param dto Objeto DTO com os novos dados do usuário.
     * @return Resposta HTTP com o usuário atualizado.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        UserDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    /**
     * Endpoint para inserir um novo usuário.
     *
     * @param dto Objeto DTO com os dados do novo usuário.
     * @return Resposta HTTP com o usuário criado e a URI do novo recurso.
     */
    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
        UserDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    /**
     * Endpoint para deletar um usuário existente.
     *
     * @param id ID do usuário a ser removido.
     * @return Resposta HTTP sem conteúdo após a remoção.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}