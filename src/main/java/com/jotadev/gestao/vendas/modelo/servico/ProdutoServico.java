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

    public String salvar(Produto produto) {
        boolean resultado = produtoRepositorioImpl.salvar(produto);
        
        if (resultado) return "Produto salvo com sucesso!";
        
        return "Erro ao salvar o produto.";
    }

    public void remover(Long produtoId) {
        produtoRepositorioImpl.removerPeloId(produtoId);
    }
    
    public Optional<Produto> buscarPeloId(Long id) {
        try {
            return produtoRepositorioImpl.buscarPeloId(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e);
            return Optional.empty();
        }
    }
}
