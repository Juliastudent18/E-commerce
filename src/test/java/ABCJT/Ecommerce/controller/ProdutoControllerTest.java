package ABCJT.Ecommerce.controller;

import ABCJT.Ecommerce.dto.ProdutoRequestDTO;
import ABCJT.Ecommerce.dto.ProdutoResponseDTO;
import ABCJT.Ecommerce.exception.ProdutoNotFoundException;
import ABCJT.Ecommerce.model.Produto;
import ABCJT.Ecommerce.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProdutoResponseDTO responseDTO;
    private ProdutoRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        Produto produto = new Produto("Notebook", "Notebook gamer", new BigDecimal("3500.00"), 10, "Eletronicos");
        produto.setId(1L);
        responseDTO = new ProdutoResponseDTO(produto);

        requestDTO = new ProdutoRequestDTO();
        requestDTO.setNome("Notebook");
        requestDTO.setDescricao("Notebook gamer");
        requestDTO.setPreco(new BigDecimal("3500.00"));
        requestDTO.setQuantidadeEstoque(10);
        requestDTO.setCategoria("Eletronicos");
    }

    @Test
    void deveCriarProduto() throws Exception {
        when(produtoService.criar(any(ProdutoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Notebook"));
    }

    @Test
    void deveListarTodosOsProdutos() throws Exception {
        when(produtoService.listarTodos()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Notebook"));
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {
        when(produtoService.buscarPorId(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deveRetornar404QuandoProdutoNaoExiste() throws Exception {
        when(produtoService.buscarPorId(99L)).thenThrow(new ProdutoNotFoundException(99L));

        mockMvc.perform(get("/produtos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveAtualizarProduto() throws Exception {
        when(produtoService.atualizar(eq(1L), any(ProdutoRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/produtos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Notebook"));
    }

    @Test
    void deveDeletarProduto() throws Exception {
        doNothing().when(produtoService).deletar(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveBuscarPorCategoria() throws Exception {
        when(produtoService.buscarPorCategoria("Eletronicos")).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/produtos/categoria?nome=Eletronicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoria").value("Eletronicos"));
    }
}