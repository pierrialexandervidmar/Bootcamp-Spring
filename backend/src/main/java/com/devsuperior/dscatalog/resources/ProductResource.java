package com.devsuperior.dscatalog.resources;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;

/**
 * Controlador REST para recursos de produtos.
 * Mapeia endpoints para operações CRUD (criação, leitura, atualização e remoção) de produtos.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    /**
     * Endpoint para buscar todos os produtos paginados.
     *
     * @param pageable Configurações de paginação e ordenação.
     * @return Resposta HTTP com a página de produtos.
     */
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    /**
     * Endpoint para buscar um produto específico pelo ID.
     *
     * @param id ID do produto.
     * @return Resposta HTTP com os dados do produto.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO item = service.findById(id);
        return ResponseEntity.ok().body(item);
    }

    /**
     * Endpoint para atualizar um produto existente.
     *
     * @param id  ID do produto a ser atualizado.
     * @param dto Objeto DTO com os novos dados do produto.
     * @return Resposta HTTP com o produto atualizado.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * Endpoint para inserir um novo produto.
     *
     * @param dto Objeto DTO com os dados do novo produto.
     * @return Resposta HTTP com o produto criado e a URI do novo recurso.
     */
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * Endpoint para deletar um produto existente.
     *
     * @param id ID do produto a ser removido.
     * @return Resposta HTTP sem conteúdo após a remoção.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
