package com.jotadev.gestao.vendas.modelo.servico;

//import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.Venda;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.VendaItemRepositorioImpl;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.VendaRepositorioImpl;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;

public class VendaServico {
    
    private VendaRepositorioImpl vendaRepositorioImpl;
    private VendaItemRepositorioImpl vendaItemRepositorioImpl;
    
    public VendaServico() {
        vendaRepositorioImpl = new VendaRepositorioImpl();
        vendaItemRepositorioImpl = new VendaItemRepositorioImpl();
    }
    
    public List<VendaDto> buscarTodos() {
        return ((VendaRepositorioImpl) vendaRepositorioImpl).encontrarTodosPersonalizado();
//        String sql = "SELECT * FROM venda ORDER BY id DESC";
//        List<VendaDto> lista = new ArrayList<>();
//
//        // O try-with-resources aqui garante que a conexão abra e feche APENAS para esta consulta
//        try (Connection conn = ConexaoMySQL.obterConexao(); 
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while(rs.next()) {
//                VendaDto venda = new VendaDto();
//    
//                    venda.setId(rs.getLong("id"));
//                    venda.setTotalVenda(rs.getBigDecimal("totalVenda"));
//                    venda.setValorPago(rs.getBigDecimal("valorPago"));
//                    venda.setTroco(rs.getBigDecimal("troco"));
//                    venda.setDesconto(rs.getBigDecimal("desconto"));
//
//                    venda.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
//
//
//                    lista.add(venda);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lista;
    }
    
    public String salvar(Venda venda, List<VendaDto> itens) {
        return vendaRepositorioImpl.salvar(venda, itens); 
    }
    
}
