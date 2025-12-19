package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.entidade.Produto;

public class ProdutoRepositorioImpl extends CrudRepositorioImpl<Produto> {

    public ProdutoRepositorioImpl() {
        super(Produto.class);
    }
    
}
