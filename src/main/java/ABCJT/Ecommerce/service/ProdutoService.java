package ABCJT.Ecommerce.service;

import ABCJT.Ecommerce.dto.ProdutoRequestDTO;
import ABCJT.Ecommerce.dto.ProdutoResponseDTO;
import ABCJT.Ecommerce.exception.ProdutoNotFoundException;
import ABCJT.Ecommerce.model.Produto;
import ABCJT.Ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // Injeção de dependência via construtor
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Criar produto
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Produto produto = new Produto(
            dto.getNome(),
            dto.getDescricao(),
            dto.getPreco(),
            dto.getQuantidadeEstoque(),
            dto.getCategoria()
        );
        return new ProdutoResponseDTO(produtoRepository.save(produto));
    }

    // Listar todos
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
            .stream()
            .map(ProdutoResponseDTO::new)
            .collect(Collectors.toList());
    }

    // Buscar por ID
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new ProdutoNotFoundException(id));
        return new ProdutoResponseDTO(produto);
    }

    // Atualizar
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new ProdutoNotFoundException(id));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setCategoria(dto.getCategoria());

        return new ProdutoResponseDTO(produtoRepository.save(produto));
    }

    // Deletar
    public void deletar(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new ProdutoNotFoundException(id));
        produtoRepository.delete(produto);
    }

    // Buscar por categoria
    public List<ProdutoResponseDTO> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria)
            .stream()
            .map(ProdutoResponseDTO::new)
            .collect(Collectors.toList());
    }
}