package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class BotaoContorno extends JButton {
    
    public BotaoContorno() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(new Color(26, 188, 254));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    protected void paintComponent(Graphics2D graphics) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.drawRoundRect(0 , 0, width - 1, height - 1, height, height);
        super.paintComponent(graphics);
    }
}
