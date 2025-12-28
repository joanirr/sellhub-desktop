package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

class TabelaCabecalho extends JLabel{
    
    public TabelaCabecalho(String texto) {
        super(texto);
        setOpaque(true);
        setBackground(new Color(50, 50, 50));
        setFont(new Font("sanserif", 1, 12));
        setForeground(new Color(200, 200, 200));
        setBorder(new EmptyBorder(10, 5, 10, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(70, 70, 70));
        g.drawLine(0, getHeight() -1, getWidth(), getHeight());
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
