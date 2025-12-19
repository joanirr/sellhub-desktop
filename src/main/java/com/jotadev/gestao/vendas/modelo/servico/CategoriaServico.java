package com.jotadev.gestao.vendas.modelo.servico;

import com.jotadev.gestao.vendas.modelo.entidade.Categoria;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.CategoriaRepositorio;
import java.util.Optional;

public class CategoriaServico {
    private CategoriaRepositorio categoriaRepositorio;
    
    public CategoriaServico() {
        categoriaRepositorio = new CategoriaRepositorio();
    }
    
    public Optional<Categoria> buscarPeloId(Long id) {
        return categoriaRepositorio.buscarPeloId(id);
    }
}
