package com.ada.web2.web1339.controller;


import com.ada.web2.web1339.dto.ProdutoDto;
import com.ada.web2.web1339.dto.ProdutoResponsePaginationDto;
import com.ada.web2.web1339.model.Produto;
import com.ada.web2.web1339.model.ProdutoEntity;
import com.ada.web2.web1339.service.ProdutoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping/*(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })*/
    public ProdutoResponsePaginationDto getProdutos(@RequestParam(required = false) String nome,
                                                    @RequestParam(required = false) Double preco,
                                                    @RequestParam(defaultValue = "0") Integer numeroPagina,
                                                    @RequestParam(defaultValue = "10") Integer tamanhoPagina) {
        return service.getProdutos(nome, preco,numeroPagina, tamanhoPagina);
    }

    @PostMapping/*(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
                 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})*/
    public ResponseEntity<Produto> adicionaProduto(@RequestBody Produto produto) {
        var produtoCriado = service.addProduto(produto.getNome(), produto.getPreco());

        return new ResponseEntity<>(produtoCriado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {
        service.deleteProduto(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        var produtoAtualizado = service.updateProduto(id, produto);

        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduto(@PathVariable Integer id) {
        var produto = service.getProduto(id);

        if(produto.isPresent()){
            return ResponseEntity.ok(produto.get());
        }

        return ResponseEntity.notFound().build();
    }
}
