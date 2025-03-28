package com.ada.web2.web1339.mapper;

import com.ada.web2.web1339.dto.ProdutoDto;
import com.ada.web2.web1339.model.ProdutoEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    List<ProdutoDto> mapProdutoDtoList(List<ProdutoEntity> produtos);

    ProdutoDto mapProdutoDto(ProdutoEntity produtoEntity);
}
