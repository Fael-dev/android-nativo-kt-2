package com.example.orgs.dao

import com.example.orgs.models.Produto
import java.math.BigDecimal
// transforma uma imagem em diferentes densidades de telas
// https://nsimage.brosteins.com/
class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(nome =  "Frutas vermelhas", descricao = "Melão, caju, laranja, limão.", valor = BigDecimal("12.90"))
        )
    }
}