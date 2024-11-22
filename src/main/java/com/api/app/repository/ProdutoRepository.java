package com.api.app.repository;
import com.api.app.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Busca produtos pelo nome ou descrição
    List<Produto> findByNomeContainingOrDescricaoContaining(String nome, String descricao);
}


