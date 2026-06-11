package ABCJT.Ecommerce.service;

import ABCJT.Ecommerce.dto.ProdutoRequestDTO;
import ABCJT.Ecommerce.dto.ProdutoResponseDTO;
import ABCJT.Ecommerce.exception.ProdutoNotFoundException;
import ABCJT.Ecommerce.model.Produto;
import ABCJT.Ecommerce.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;
    private ProdutoRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        produto = new Produto("Notebook", "Notebook gamer", new BigDecimal("3500.00"), 10, "Eletronicos");
        produto.setId(1L);

        requestDTO = new ProdutoRequestDTO();
        requestDTO.setNome("Notebook");
        requestDTO.setDescricao("Notebook gamer");
        requestDTO.setPreco(new BigDecimal("3500.00"));
        requestDTO.setQuantidadeEstoque(10);
        requestDTO.setCategoria("Eletronicos");
    }

    @Test
    void deveCriarProdutoComSucesso() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoResponseDTO resultado = produtoService.criar(requestDTO);

        assertNotNull(resultado);
        assertEquals("Notebook", resultado.getNome());
        assertEquals("Eletronicos", resultado.getCategoria());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void deveListarTodosOsProdutos() {
        when(produtoRepository.findAll()).thenReturn(List.of(produto));

        List<ProdutoResponseDTO> resultado = produtoService.listarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Notebook", resultado.get(0).getNome());
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        ProdutoResponseDTO resultado = produtoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProdutoNotFoundException.class, () -> produtoService.buscarPorId(99L));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoResponseDTO resultado = produtoService.atualizar(1L, requestDTO);

        assertNotNull(resultado);
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        assertDoesNotThrow(() -> produtoService.deletar(1L));
        verify(produtoRepository, times(1)).delete(produto);
    }

    @Test
    void deveBuscarPorCategoria() {
        when(produtoRepository.findByCategoria("Eletronicos")).thenReturn(List.of(produto));

        List<ProdutoResponseDTO> resultado = produtoService.buscarPorCategoria("Eletronicos");

        assertEquals(1, resultado.size());
        assertEquals("Eletronicos", resultado.get(0).getCategoria());
    }
}