package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.entidade.Categoria;

public class CategoriaRepositorio extends CrudRepositorioImpl {
    
    public CategoriaRepositorio() {
        super(Categoria.class);
    }
    
    
    
    public static void main(String[] args) {
        Categoria categoria = Categoria.builder()
                .id(1L)
                .nome("Livro")
                .descricao("Conhecimento e poder")
                .build();
        
        CategoriaRepositorio repositorio = new CategoriaRepositorio();
        
        System.out.println("RESULTADO: " + repositorio.removerPeloId(2L));
    }
}
