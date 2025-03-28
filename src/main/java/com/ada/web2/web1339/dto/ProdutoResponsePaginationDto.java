package com.ada.web2.web1339.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponsePaginationDto {

    //paginacao info
    private Integer totalPaginas;
    private Long totalElementos;
    private Integer quantidadeItensPorPagina;
    private Integer paginaAtual;
    //produtos
    private List<ProdutoDto> produtos;
}
