package com.ada.web2.web1339.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@XmlRootElement
public class Produto {

    private static int idAuto = 0;
    private Integer id;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.id = ++idAuto;
        this.nome = nome;
        this.preco = preco;
    }

}
