package com.devsuperior.dscatalog.resources;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

/**
 * Controlador REST para recursos de categorias.
 * Mapeia endpoints para operações CRUD (criação, leitura, atualização e remoção) de categorias.
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    /**
     * Endpoint para buscar todas as categorias paginadas.
     *
     * @param page         Número da página (opcional, valor padrão 0).
     * @param linesPerPage Número de linhas por página (opcional, valor padrão 12).
     * @param direction    Direção da ordenação (ASC ou DESC, valor padrão ASC).
     * @param orderBy      Campo pelo qual os resultados serão ordenados (opcional, valor padrão "name").
     * @return Resposta HTTP com a página de categorias.
     */
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<CategoryDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para buscar uma categoria específica pelo ID.
     *
     * @param id ID da categoria.
     * @return Resposta HTTP com os dados da categoria.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO item = service.findById(id);
        return ResponseEntity.ok().body(item);
    }

    /**
     * Endpoint para atualizar uma categoria existente.
     *
     * @param id  ID da categoria a ser atualizada.
     * @param dto Objeto DTO com os novos dados da categoria.
     * @return Resposta HTTP com a categoria atualizada.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * Endpoint para inserir uma nova categoria.
     *
     * @param dto Objeto DTO com os dados da nova categoria.
     * @return Resposta HTTP com a categoria criada e a URI do novo recurso.
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * Endpoint para deletar uma categoria existente.
     *
     * @param id ID da categoria a ser removida.
     * @return Resposta HTTP sem conteúdo após a remoção.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
