package ABCJT.Ecommerce.controller;

import ABCJT.Ecommerce.dto.ProdutoRequestDTO;
import ABCJT.Ecommerce.dto.ProdutoResponseDTO;
import ABCJT.Ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de produtos do e-commerce")
public class ProdutoController {

    private final ProdutoService produtoService;

    // Injeção de dependência via construtor
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // POST /produtos - Criar produto
    @PostMapping
    @Operation(summary = "Criar um novo produto")
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(dto));
    }

    // GET /produtos - Listar todos
    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    // GET /produtos/{id} - Buscar por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    // PUT /produtos/{id} - Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto existente")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    // DELETE /produtos/{id} - Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /produtos/categoria?nome=eletronicos - Buscar por categoria
    @GetMapping("/categoria")
    @Operation(summary = "Buscar produtos por categoria")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoria(
            @RequestParam String nome) {
        return ResponseEntity.ok(produtoService.buscarPorCategoria(nome));
    }
}
