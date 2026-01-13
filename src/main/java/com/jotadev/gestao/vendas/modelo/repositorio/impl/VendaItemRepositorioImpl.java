package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.VendaItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaItemRepositorioImpl extends CrudRepositorioImpl<VendaItem> {
    
    public VendaItemRepositorioImpl() {
        super(VendaItem.class);
    }
    
    public List<VendaDto> buscarItensPorVendaId(Long vendaId) {
        String sql = "SELECT p.nome, vi.quantidade, vi.preco_unitario, vi.desconto, vi.subtotal " +
                     "FROM venda_item vi " +
                     "JOIN produto p ON vi.produtoId = p.id " +
                     "WHERE vi.vendaId = ?";

        List<VendaDto> itens = new ArrayList<>();

        try (Connection conn = ConexaoMySQL.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, vendaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VendaDto item = new VendaDto();
                item.setNome(rs.getString("nome"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPreco(rs.getDouble("preco_unitario"));
                item.setDesconto(rs.getBigDecimal("desconto"));
                item.setSubtotal(rs.getDouble("subtotal"));
                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }
    
    public void removerPorVendaId(Long vendaId) {
        String sql = "DELETE FROM vendaitem WHERE vendaId = ?";
        try (Connection conn = ConexaoMySQL.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, vendaId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao limpar itens da venda: " + e.getMessage());
        }
    }
}
