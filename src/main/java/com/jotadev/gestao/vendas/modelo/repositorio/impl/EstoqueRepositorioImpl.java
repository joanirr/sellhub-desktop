package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.entidade.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EstoqueRepositorioImpl extends CrudRepositorioImpl<Estoque> {
    
    public EstoqueRepositorioImpl() {
        super(Estoque.class);
    }
    
    public Optional<Estoque> buscarPeloProdutoId(Long produtoId) {
        try {
            String SQL = String.format("SELECT * FROM estoque WHERE produtoid = ?");
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
            
            ps.setLong(1, produtoId);
            
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                return Optional.ofNullable(getT(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return Optional.empty();
    }
    public int somarTodosItens() {
        String sql = "SELECT SUM(quantidade) FROM estoque";
        try (Connection conn = ConexaoMySQL.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public long contarTodos() {
        String sql = "SELECT COUNT(*) FROM  + estoque";
        try (Connection conn = ConexaoMySQL.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getLong(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
}
