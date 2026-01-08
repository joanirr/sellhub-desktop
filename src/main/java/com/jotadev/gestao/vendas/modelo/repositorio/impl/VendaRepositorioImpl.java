package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaRepositorioImpl extends CrudRepositorioImpl<Venda>{
    
    public VendaRepositorioImpl() {
        super(Venda.class);
    }
    
    public List<VendaDto> encontrarTodosPersonalizado() {
        String sql = "SELECT v.*, u.nome AS usuario_nome, c.nome AS cliente_nome " +
                     "FROM venda v " +
                     "INNER JOIN usuario u ON v.usuarioId = u.id " +
                     "LEFT JOIN cliente c ON v.clienteId = c.id " +
                     "ORDER BY v.id DESC";

        List<VendaDto> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VendaDto v = new VendaDto();
                v.setId(rs.getLong("id"));
                v.setTotalVenda(rs.getBigDecimal("totalVenda"));
                v.setValorPago(rs.getBigDecimal("valorPago"));
                v.setTroco(rs.getBigDecimal("troco"));
                v.setDesconto(rs.getBigDecimal("desconto"));
                v.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());

                // Mapeando os nomes para os campos que a Tabela vai ler
                v.setUsuario(rs.getString("usuario_nome"));
                String nomeCli = rs.getString("cliente_nome");
                v.setCliente(nomeCli != null ? nomeCli : "Consumidor Final");

                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    private VendaDto getVendaDto(ResultSet resultSet) throws SQLException {
        return VendaDto.builder()
                .id(resultSet.getLong("id"))
                .totalVenda(resultSet.getBigDecimal("totalVenda"))
                .troco(resultSet.getBigDecimal("troco"))
                .desconto(resultSet.getBigDecimal("desconto"))
                .cliente(resultSet.getString("cpf"))
                .dataCriacao(resultSet.getObject("dataCriacao", LocalDateTime.class))
                .ultimaAtualizacao(resultSet.getObject("ultimaAtualizacao", LocalDateTime.class))
                .usuario(resultSet.getString("nome"))
                .valorPago(resultSet.getBigDecimal("valorPago"))
                .vendaItemDto(null)
                .build();
    }
    public String salvar(Venda venda, List<VendaDto> itens) {
        // SQL deve bater com os nomes das colunas do Workbench
        String sqlVenda = "INSERT INTO venda (totalVenda, valorPago, troco, desconto, clienteId, usuarioId, dataCriacao) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoMySQL.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

            ps.setBigDecimal(1, venda.getTotalVenda());
            ps.setBigDecimal(2, venda.getValorPago()); 
            ps.setBigDecimal(3, venda.getTroco());
            ps.setBigDecimal(4, venda.getDesconto());
            ps.setLong(5, 1L); 
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            
            if (venda.getUsuarioId() != null) {
                ps.setLong(6, venda.getUsuarioId());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            // O COMANDO QUE ENVIA PARA O BANCO
            int linhasAfetadas = ps.executeUpdate(); 

            if (linhasAfetadas > 0) {
                return "Venda realizada com sucesso!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro SQL: " + e.getMessage();
        }
        return "Erro desconhecido";
    }
    
}
