package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaRepositorioImpl extends CrudRepositorioImpl<Venda>{
    
    public VendaRepositorioImpl() {
        super(Venda.class);
    }
    
    public List<VendaDto> encontrarTodosPersonalizado() {
        String SQL = "SELECT v.*, u.nome, c.cpf FROM venda v, cliente c, usuario u "
                + "WHERE v.clienteid = c.id AND v.usuarioId = u.id ORDER BY v.id";
        List<VendaDto> lista = new ArrayList<>();
        try {
            ResultSet resultSet = ConexaoMySQL.obterConexao()
                    .prepareStatement(SQL)
                    .executeQuery();
            
            while(resultSet.next()) {
                lista.add(getVendaDto(resultSet));
            } 
        } catch (Exception e) {
            System.err.println(e);
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
}
