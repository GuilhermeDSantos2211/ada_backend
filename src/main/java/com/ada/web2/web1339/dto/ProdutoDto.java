package com.ada.web2.web1339.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {
    private Long id;
    private String nome;
    private Double preco;
}
