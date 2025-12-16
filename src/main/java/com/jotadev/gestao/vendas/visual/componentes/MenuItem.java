package com.jotadev.gestao.vendas.visual.componentes;

import com.jotadev.gestao.vendas.visual.modelo.MenuModelo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuItem extends javax.swing.JPanel {
    
    private boolean selecionado;
    private boolean hover;
    

    public MenuItem(MenuModelo menuModelo) {
        initComponents();
        setOpaque(false);
        
        switch(menuModelo.getTipoMenu()) {
            case MENU -> {
                labelMenuItemIcone.setIcon(menuModelo.toIcon());
                labelMenuItemNome.setText(menuModelo.getNome());
            }
            case TITULO -> {
                labelMenuItemNome.setText(menuModelo.getNome());
                labelMenuItemNome.setFont(new Font("sanserif", 1, 12));
                labelMenuItemNome.setForeground(Color.white);
                labelMenuItemIcone.setVisible(false);
            }
            case VAZIO -> {
                labelMenuItemNome.setText("");
            }
        }
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        repaint();
    }

    public void setHover(boolean hover) {
        this.hover = hover;
        repaint();
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        
        if (selecionado || hover) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (selecionado) {
                graphics2D.setColor(new Color(255, 255, 255, 80));
            } else {
                graphics2D.setColor(new Color(255, 255, 255, 80));
            }
            graphics2D.fillRoundRect(10, 0, getWidth() -20, getHeight(), 5, 5);
        }
        
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMenuItemIcone = new javax.swing.JLabel();
        labelMenuItemNome = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(172, 31));

        labelMenuItemIcone.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        labelMenuItemNome.setForeground(new java.awt.Color(255, 255, 255));
        labelMenuItemNome.setText("MenuItem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelMenuItemIcone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMenuItemNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMenuItemIcone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMenuItemNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMenuItemIcone;
    private javax.swing.JLabel labelMenuItemNome;
    // End of variables declaration//GEN-END:variables
}
