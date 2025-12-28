package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Tabela extends JTable {

    private final Color backgroundCell = new Color(35, 35, 35);
    private final Color foregroundCell = new Color(200, 200, 200);
    private final Color gridDark = new Color(70, 70, 70);
    private final Color selectedText = new Color(13, 113, 182);

    public Tabela() {
        setShowHorizontalLines(true);
        setRowHeight(30);
        setGridColor(gridDark);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TabelaCabecalho cabecalho = new TabelaCabecalho(value + "");
                cabecalho.setBackground(new Color(50, 50, 50));
                cabecalho.setForeground(foregroundCell);
                return cabecalho;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value != null && "REMOVER".equals(value)) {
                    return botao(String.valueOf(value));
                }

                component.setBackground(backgroundCell);
                component.setForeground(isSelected ? selectedText : foregroundCell);

                return component;
            }
        });
    }

    public JButton botao(String texto) {
        BotaoContorno botao = new BotaoContorno();
        botao.setText(texto);
        botao.setForeground(new Color(204, 0, 0));
        botao.setBackground(new Color(204, 0, 0));
        return botao;
    }
}
