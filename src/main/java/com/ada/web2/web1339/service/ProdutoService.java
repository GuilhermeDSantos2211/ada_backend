package com.ada.web2.web1339.service;

import com.ada.web2.web1339.dto.ProdutoResponsePaginationDto;
import com.ada.web2.web1339.mapper.ProdutoMapper;
import com.ada.web2.web1339.model.Produto;
import com.ada.web2.web1339.model.ProdutoEntity;
import com.ada.web2.web1339.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class ProdutoService {

    private static final List<Produto> produtos = new ArrayList<>();

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;

        List<ProdutoEntity> produtos = new ArrayList<>();

        //add 11 produtos
        produtos.add(new ProdutoEntity(null, "Pão", 1.0));
        produtos.add(new ProdutoEntity(null, "Leite", 1.5));
        produtos.add(new ProdutoEntity(null, "Queijo", 3.2));
        produtos.add(new ProdutoEntity(null, "Presunto", 2.8));
        produtos.add(new ProdutoEntity(null, "Manteiga", 2.0));
        produtos.add(new ProdutoEntity(null, "Arroz", 1.9));
        produtos.add(new ProdutoEntity(null, "Feijão", 2.3));
        produtos.add(new ProdutoEntity(null, "Açúcar", 1.7));
        produtos.add(new ProdutoEntity(null, "Café", 4.5));
        produtos.add(new ProdutoEntity(null, "Chocolate", 3.8));
        produtos.add(new ProdutoEntity(null, "Pão 2", 3.8));

        produtoRepository.saveAll(produtos);
    }

    public ProdutoResponsePaginationDto getProdutos(String nome, Double preco, Integer paginaNumero, Integer tamanhoPagina) {

        Pageable pageable = PageRequest.of(paginaNumero, tamanhoPagina, Sort.by("nome").and(Sort.by("preco").ascending()));

        //List<ProdutoEntity> produtosDb = produtoRepository.findAll();
        //List<ProdutoEntity> produtosDb = produtoRepository.findAllByNomeOrPreco(nome, preco);
        Page paginaProdutos = produtoRepository.searchPorNomeOuPreco(nome, preco, pageable);

        var produtosDto = produtoMapper.mapProdutoDtoList(paginaProdutos.getContent());

        return new ProdutoResponsePaginationDto(paginaProdutos.getTotalPages(),
                paginaProdutos.getTotalElements(),
                paginaProdutos.getSize(),
                paginaProdutos.getNumber(),
                produtosDto);
    }

    public Produto addProduto(String nome, double preco) {

        var produto = new Produto(nome, preco);
        produtos.add(produto);

        return produto;
    }

    public void deleteProduto(Integer id) {
        produtos.removeIf(produto -> produto.getId().equals(id));
    }

    public Produto updateProduto(Integer id, Produto produto) {
        produto.setId(id);
        produtos.stream()
                .filter(p -> p.getId() == id).findFirst().ifPresent(p -> {
                    p.setNome(produto.getNome());
                    p.setPreco(produto.getPreco());
                });

        return produto;
    }

    public Optional<Produto> getProduto(Integer id) {
        /*for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return Optional.of(produto);
            }
        }
        return Optional.empty();*/

        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
