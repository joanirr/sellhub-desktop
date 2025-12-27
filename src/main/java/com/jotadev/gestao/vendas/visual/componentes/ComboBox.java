package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComboBox;

public class ComboBox extends JComboBox<Object> {
    
    public ComboBox() {
        setBackground(new Color(0,0,0,0));
        setForeground(new Color(220, 220, 220));
        setFont(new Font("sanserif", 0, 13));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(70, 70, 70));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        super.paintComponent(g);
    }
}
