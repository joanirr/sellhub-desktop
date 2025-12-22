package com.jotadev.gestao.vendas.modelo.servico;

import com.jotadev.gestao.vendas.modelo.entidade.Categoria;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.CategoriaRepositorio;
import java.util.List;
import java.util.Optional;

public class CategoriaServico {
    private CategoriaRepositorio categoriaRepositorio;
    
    public CategoriaServico() {
        categoriaRepositorio = new CategoriaRepositorio();
    }
    
    public Optional<Categoria> buscarPeloId(Long id) {
        return categoriaRepositorio.buscarPeloId(id);
    }
    
    public List<Categoria> buscarTodos() {
        return categoriaRepositorio.buscarTodos();
    }
    
    public String salvar(Categoria categoria) {
        try {
            boolean resultado = categoriaRepositorio.salvar(categoria);
            
            if (resultado) return "Categoria salva com sucesso!";
            else return "Erro ao salvar.";
        } catch (Exception e) {
            if(e.getMessage().contains("Duplicate entry")) return "A categoria " + categoria.getNome() + " já existe.";
            
            return "Erro ao salvar";
        }
    }
    
    public void removerPeloId(Long id) {
        try {
            categoriaRepositorio.removerPeloId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Categoria> buscarPeloNome(String categoriaNome) {
        return categoriaRepositorio.buscarPeloNome(categoriaNome);
    }
}
