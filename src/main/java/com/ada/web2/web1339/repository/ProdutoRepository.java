package com.ada.web2.web1339.repository;

import com.ada.web2.web1339.model.ProdutoEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    //query methods
    List<ProdutoEntity> findAllByNomeOrPreco(String nome, Double preco);

    //query com jpql
    @Query("SELECT p FROM ProdutoEntity p WHERE " +
            "(:nomeParam IS NULL OR p.nome = :nomeParam) AND (:precoParam IS NULL OR p.preco = :precoParam)")
    Page<ProdutoEntity> searchPorNomeOuPreco(String nomeParam, Double precoParam, Pageable pageable);

    //query native
    @Query(value = "SELECT * FROM produtos WHERE nome = :nomeParam OR preco = :precoParam", nativeQuery = true)
    List<ProdutoEntity> searchPorNomeOuPrecoNative(String nomeParam, Double precoParam);
}

