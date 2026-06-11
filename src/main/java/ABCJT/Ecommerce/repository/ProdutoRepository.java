package ABCJT.Ecommerce.repository;

import ABCJT.Ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Busca produtos por categoria
    List<Produto> findByCategoria(String categoria);

    // Busca produtos por nome (contendo o texto)
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
