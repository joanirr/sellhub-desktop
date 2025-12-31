package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class CampoDeSenha extends JPasswordField {
    
    private String dicas;
    private Icon prefixoIcon;
    private Color cor;
    
    public CampoDeSenha() {
        setUI(new javax.swing.plaf.basic.BasicPasswordFieldUI());
    
        setBackground(new Color(0, 35, 25));
        setForeground(new Color(255, 255, 255));
        setCaretColor(new Color(29, 185, 84));
    
        setSelectionColor(new Color(29, 185, 84, 150)); 
        setSelectedTextColor(Color.WHITE);
    
        setFont(new Font("sanserif", 0, 13));
        setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(62, 62, 62), 1),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        setOpaque(false);
        this.cor = new Color(0, 35, 25);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0, 35, 25));

        int r = getHeight(); 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), r, r);

        g2.dispose();

        super.paintComponent(g);
        paintIcon(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        if (getText().length() == 0) {
            int altura = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(150, 150, 150));
            g.drawString(dicas, ins.left, altura / 2 + fm.getAscent() / 2 - 2);
        }
    }
    
    private void paintIcon(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        if (prefixoIcon != null) {
            Image prefixo = ((ImageIcon) prefixoIcon).getImage();
            
            int altura = (getHeight() - prefixoIcon.getIconHeight()) / 2;
            
            graphics2D.drawImage(prefixo, 10, altura, this);
        }
    }
    
    private void inicializarBorda() {
        int esquerda = 10;
        
        if (prefixoIcon != null) {
            esquerda = prefixoIcon.getIconHeight() +15;
        }
        
        setBorder(BorderFactory.createEmptyBorder(10, esquerda, 10, 10));
    }

    public String getDicas() {
        return dicas;
    }

    public void setDicas(String dicas) {
        this.dicas = dicas;
    }

    public Icon getPrefixoIcon() {
        return prefixoIcon;
    }

    public void setPrefixoIcon(Icon prefixoIcon) {
        this.prefixoIcon = prefixoIcon;
        inicializarBorda();
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    
}
