package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.entidade.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class CategoriaRepositorio extends CrudRepositorioImpl {
    
    public CategoriaRepositorio() {
        super(Categoria.class);
    }
    
    public Optional<Categoria> buscarPeloNome(String nome) {
        String sql = "SELECT * FROM categoria WHERE nome = ?";
        
        try {
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(sql);
            ps.setString(1, nome);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) return Optional.ofNullable((Categoria) getT(rs));
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
