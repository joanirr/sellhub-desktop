package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Mensagem extends javax.swing.JPanel {
    
    private TipoMensagem tipoMensagem;
    private boolean mostrar;
    
    public Mensagem() {
        initComponents();
        setVisible(false);
        setOpaque(false);
    }
    
    public enum TipoMensagem {
        SUCESSO, ERRO
    }
    
    public void mostrarMensagem(TipoMensagem tipo, String mensagem) {
        this.tipoMensagem = tipo;
        labelMensagem.setText(mensagem);
        
        String caminho = System.getProperty("user.dir") + "\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\";
        
        if (tipo.equals(TipoMensagem.SUCESSO)) {
            labelMensagem.setIcon(new ImageIcon(caminho + "sucesso.png"));
        } else {
            labelMensagem.setIcon(new ImageIcon(caminho + "erro.png"));
        }
       
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        if (this.tipoMensagem == TipoMensagem.SUCESSO) {
            graphics2D.setColor(new Color(46, 204, 113));
        } else {
            graphics2D.setColor(new Color(231, 76, 60));
        }
        
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        
        graphics2D.setComposite(AlphaComposite.SrcOver);
        graphics2D.setColor(new Color(70, 70, 70));
        graphics2D.drawRect(0, 0, getWidth() -1, getHeight());
        
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMensagem = new javax.swing.JLabel();

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setText("Mensagem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMensagem;
    // End of variables declaration//GEN-END:variables
}
