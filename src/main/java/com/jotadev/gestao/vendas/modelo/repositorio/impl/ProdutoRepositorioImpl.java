package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class ProdutoRepositorioImpl extends CrudRepositorioImpl<Produto> {

    public ProdutoRepositorioImpl() {
        super(Produto.class);
    }

    private Produto mapearProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getLong("id"));
        produto.setNome(rs.getString("nome"));
        produto.setPreco(rs.getBigDecimal("preco"));
        
        int qtdNoBanco = rs.getInt("quantidade");
        produto.setQuantidade(rs.wasNull() ? 0 : qtdNoBanco);
        
        Object catIdObj = rs.getObject("categoriaId"); 
        if (catIdObj != null) {
            produto.setCategoriaId(rs.getLong("categoriaId"));
        }
        return produto;
    }

    @Override
        public List<Produto> buscarTodos() {
            List<Produto> lista = new ArrayList<>();
            String SQL = "SELECT * FROM produto";

            try (PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearProduto(rs));
                }
            } catch (SQLException e) {
                System.err.println("ERRO NO BUSCAR TODOS: " + e.getMessage());
            }
            return lista;
        }
    
    public Optional<Produto> buscarPeloNome(String nome) {
        String SQL = "SELECT * FROM produto WHERE nome = ?";
        try (PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL)) {
            ps.setString(1, nome);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearProduto(rs)); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Produto> buscarPeloId(Long id) {
        String SQL = "SELECT * FROM produto WHERE id = ?";
        try (PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public void atualizarEstoque(Long produtoId, int novaQuantidade) {
        String SQL = "UPDATE produto SET quantidade = ? WHERE id = ?";
        try (PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL)) {
            ps.setInt(1, novaQuantidade);
            ps.setLong(2, produtoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Long contarTodos() {
        String sql = "SELECT COUNT(*) FROM produto";
        try (Connection conn = ConexaoMySQL.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}