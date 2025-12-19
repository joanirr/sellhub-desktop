package com.jotadev.gestao.vendas.modelo.servico;

import com.jotadev.gestao.vendas.modelo.dto.ProdutoDto;
import com.jotadev.gestao.vendas.modelo.entidade.Categoria;
import com.jotadev.gestao.vendas.modelo.entidade.Produto;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.ProdutoRepositorioImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoServico {
    
    private ProdutoRepositorioImpl produtoRepositorioImpl;
    private CategoriaServico categoriaServico;

    public ProdutoServico() {
        produtoRepositorioImpl = new ProdutoRepositorioImpl();
        categoriaServico = new CategoriaServico();
    }
    
    public List<ProdutoDto> buscarTodos() {
        List<ProdutoDto> produtosDto = new ArrayList<>();
        try {
            produtosDto = produtoRepositorioImpl.buscarTodos()
                    .stream()
                    .map(p -> {
                        Optional<Categoria> categoria = categoriaServico.buscarPeloId(p.getCategoriaId());
                        
                        return ProdutoDto.builder()
                                .id(p.getId())
                                .nome(p.getNome())
                                .descricao(p.getDescricao())
                                .preco(p.getPreco())
                                .categoria(categoria.get().getNome())
                                .build();
                    })
                    .toList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return produtosDto;
    }
}
