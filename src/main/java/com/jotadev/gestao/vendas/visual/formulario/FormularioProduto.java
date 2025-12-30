package com.jotadev.gestao.vendas.visual.formulario;

import com.jotadev.gestao.vendas.controlador.FormularioProdutoController;
import com.jotadev.gestao.vendas.visual.formulario.produto.Tela;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;

public class FormularioProduto extends javax.swing.JPanel {
    
    private FormularioProdutoController formularioProdutoController;
    private Dashboard dashboard;
    private Tela tela;
    private Long usuarioId;

    public FormularioProduto(Long usuarioId, Dashboard dashboard) {
        initComponents();
        
        setOpaque(true);
        jScrollPane1.getViewport().setBackground(new Color(45,45,45));
        
        this.usuarioId = usuarioId;
        this.dashboard = dashboard;
        
        tela = new Tela(this);
        formularioProdutoController = new FormularioProdutoController(this);
        
        
        eventoDosBotoes();
        eventoDoMouse();
    }
    
    public FormularioProdutoController getFormularioProdutoController() {
        return formularioProdutoController;
    }
    
    public Tela getTela() {
        return tela;
    }
    
    public Dashboard getDashboard() {
        return dashboard;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    
    private void eventoDosBotoes() {
        botaoAtualizar.addActionListener(formularioProdutoController);
        botaoAdicionar.addActionListener(formularioProdutoController);
        botaoImprimir.addActionListener(formularioProdutoController);
        botaoRemover.addActionListener(formularioProdutoController);
    }
    
    private void eventoDoMouse() {
        tabelaProduto.addMouseListener(formularioProdutoController);
    }
    
    public JTable getTabelaProduto() {
        return tabelaProduto;
    }
    
    public JButton getBotaoAdicionar() {
        return botaoAdicionar;
    }
    
     public JButton getBotaoAtualizar() {
        return botaoAtualizar;
    }
     
    public JButton getBotaoRemover() {
        return botaoRemover;
    }
       
    public JButton getBotaoImprimir() {
        return botaoImprimir;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProduto = new com.jotadev.gestao.vendas.visual.componentes.Tabela();

        setBackground(new java.awt.Color(45, 45, 45));
        setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Produto");

        jPanel1.setBackground(new java.awt.Color(45, 45, 45));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 8, 0));

        botaoAdicionar.setBackground(new java.awt.Color(25, 55, 65));
        botaoAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.setName("adicionar"); // NOI18N
        botaoAdicionar.addActionListener(this::botaoAdicionarActionPerformed);
        jPanel1.add(botaoAdicionar);

        botaoAtualizar.setBackground(new java.awt.Color(25, 55, 65));
        botaoAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.setActionCommand("atualizar");
        botaoAtualizar.setName("atualizar"); // NOI18N
        botaoAtualizar.addActionListener(this::botaoAtualizarActionPerformed);
        jPanel1.add(botaoAtualizar);

        botaoRemover.setBackground(new java.awt.Color(25, 55, 65));
        botaoRemover.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoRemover.setForeground(new java.awt.Color(255, 255, 255));
        botaoRemover.setText("Remover");
        botaoRemover.setActionCommand("remover");
        botaoRemover.setName("remover"); // NOI18N
        botaoRemover.addActionListener(this::botaoRemoverActionPerformed);
        jPanel1.add(botaoRemover);

        botaoImprimir.setBackground(new java.awt.Color(25, 55, 65));
        botaoImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoImprimir.setForeground(new java.awt.Color(255, 255, 255));
        botaoImprimir.setText("Imprimir");
        botaoImprimir.setActionCommand("imprimir");
        botaoImprimir.setName("imprimir"); // NOI18N
        jPanel1.add(botaoImprimir);

        tabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaProduto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAdicionarActionPerformed

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaProduto;
    // End of variables declaration//GEN-END:variables
}
