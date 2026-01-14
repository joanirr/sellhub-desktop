package com.jotadev.gestao.vendas.modelo.servico;

//import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.Venda;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.VendaItemRepositorioImpl;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.VendaRepositorioImpl;
import java.time.LocalDateTime;
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
        try {
            // Salva a venda e recupera o ID gerado pelo banco
            Long vendaIdGerado = vendaRepositorioImpl.salvarERetornarId(venda);

            // Salva cada item vinculado a esse ID
            for (VendaDto item : itens) {
                vendaItemRepositorioImpl.salvarItem(item, vendaIdGerado);
            }

            return "Venda realizada com sucesso!";
        } catch (Exception e) {
            return "Erro ao salvar: " + e.getMessage();
        }
    }
    
    public List<VendaDto> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepositorioImpl.buscarPorPeriodo(inicio, fim);
    }
    
    public Long contarTotalVendas() {
        return vendaRepositorioImpl.contarTodos(); 
    }
    
    public String excluir(Long id) {
        try {
            vendaItemRepositorioImpl.removerPorVendaId(id); 

            vendaRepositorioImpl.removerPeloId(id);

            return "Venda #" + id + " excluída com sucesso!";
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir venda: " + e.getMessage());
        }
    }
    public List<VendaDto> buscarItensDaVenda(Long vendaId) {
        return vendaItemRepositorioImpl.buscarItensPorVendaId(vendaId);
    }
    
    public String gerarTextoComprovante(Long vendaId) {
        List<VendaDto> itens = vendaItemRepositorioImpl.buscarItensPorVendaId(vendaId);
        
        System.out.println("DEBUG: Buscando itens para a venda ID: " + vendaId);
        System.out.println("DEBUG: Quantidade de itens encontrados: " + itens.size());

        StringBuilder cupom = new StringBuilder();
        cupom.append("          LOJA SELLHUB          \n");
        cupom.append("--------------------------------\n");
        cupom.append("COMPROVANTE DE VENDA: #").append(vendaId).append("\n");
        cupom.append("DATA: ").append(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");
        cupom.append("--------------------------------\n");
        cupom.append(String.format("%-14s %-3s %-10s\n", "ITEM", "QTD", "SUBTOTAL"));

        double totalGeral = 0;
        for (VendaDto item : itens) {
            System.out.println("Item encontrado: " + item.getNome() + " - Valor: " + item.getSubtotal());
            String nomeCurto = item.getNome().length() > 14 ? item.getNome().substring(0, 11) + "..." : item.getNome();
            cupom.append(String.format("%-14s %-3d R$%-9.2f\n", 
                nomeCurto, item.getQuantidade(), item.getSubtotal()));
            totalGeral += item.getSubtotal();
        }

        cupom.append("--------------------------------\n");
        cupom.append(String.format("VALOR TOTAL: R$ %.2f\n", totalGeral));
        cupom.append("--------------------------------\n");
        cupom.append("   OBRIGADO PELA PREFERENCIA!   \n");

        return cupom.toString();
    }
}
