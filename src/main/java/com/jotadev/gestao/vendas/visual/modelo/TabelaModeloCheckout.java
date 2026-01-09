package com.jotadev.gestao.vendas.visual.modelo;

import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloCheckout extends AbstractTableModel {
    
    private final String [] colunas = {"ID", "Produto", "Quantidade", "Preço Unit.", "Desconto", "Subtotal"};
    private List<VendaDto> itens;
    
    public TabelaModeloCheckout(List<VendaDto> itens) {
        this.itens = itens;
    }

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VendaDto item = itens.get(rowIndex);
        
        return switch(columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getNome();
            case 2 -> item.getQuantidade();
            case 3 -> String.format("R$ %.2f", item.getPreco());
            case 4 -> String.format("R$ %.2f", item.getDesconto());
            case 5 -> String.format("R$ %.2f", item.getSubtotal());
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public void adicionar(VendaDto item) {
        this.itens.add(item);
        fireTableDataChanged();
    }
    
    public void limpar() {
        this.itens.clear();
        fireTableDataChanged();
    }
    
    public void remover(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < itens.size()) {
            this.itens.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex); //avisa a tabela que uma linha sumiu
        }
    }
    
    public List<VendaDto> getItens() {
        return this.itens;
    }
}

