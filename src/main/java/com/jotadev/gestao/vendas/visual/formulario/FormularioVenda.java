package com.jotadev.gestao.vendas.visual.formulario;

import com.jotadev.gestao.vendas.controlador.FormularioVendaController;
import com.jotadev.gestao.vendas.modelo.util.MensagemUtil;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

public class FormularioVenda extends javax.swing.JPanel {
    
    private FormularioVendaController formularioVendaController;
    private Long usuarioId;
    private MigLayout layout;
    private MensagemUtil mensagemUtil;

    public FormularioVenda(Long usuarioId) {
        initComponents();
        
        setOpaque(false);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        dialogVenda.setResizable(false);
        
        this.usuarioId = usuarioId;
        
        layout = new MigLayout("fill, insets");
        background.setLayout(layout);
        background.add(panelBoard1);
        
        formularioVendaController = new FormularioVendaController(this);
        eventoDosBotoes();
        eventoDoMouse();
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    
    private void eventoDosBotoes() {
        botaoAtualizar.addActionListener(formularioVendaController);
        botaoAdicionar.addActionListener(formularioVendaController);
        botaoImprimir.addActionListener(formularioVendaController);
        botaoPermissoes.addActionListener(formularioVendaController);
        botaoRemover.addActionListener(formularioVendaController);
    }
    
    private void eventoDoMouse() {
//        tabelaVendas.addMouseListener(formularioUsuarioController);
    }
    
    public JDialog getDialogUsuario() {
        return dialogVenda;
    }
    
    public JTable getTabelaUsuarios() {
        return tabelaVendas;
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
      
    public JButton getBotaoPermissoes() {
        return botaoPermissoes;
    }
       
    public JButton getBotaoImprimir() {
        return botaoImprimir;
    }

    public JDialog getDialogVenda() {
        return dialogVenda;
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogVenda = new javax.swing.JDialog();
        background = new javax.swing.JLayeredPane();
        panelBoard1 = new com.jotadev.gestao.vendas.visual.componentes.PanelBoard();
        jLabel2 = new javax.swing.JLabel();
        panelCirculo1 = new com.jotadev.gestao.vendas.visual.componentes.PanelCirculo();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoPermissoes = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new com.jotadev.gestao.vendas.visual.componentes.Tabela();
        labelDataInicial = new javax.swing.JLabel();
        dataInicial = new com.toedter.calendar.JDateChooser();
        labelDataFinal = new javax.swing.JLabel();
        dataFinal = new com.toedter.calendar.JDateChooser();
        botaoPesquisar = new javax.swing.JButton();

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dialogVendaLayout = new javax.swing.GroupLayout(dialogVenda.getContentPane());
        dialogVenda.getContentPane().setLayout(dialogVendaLayout);
        dialogVendaLayout.setHorizontalGroup(
            dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        dialogVendaLayout.setVerticalGroup(
            dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registro da venda");

        javax.swing.GroupLayout panelCirculo1Layout = new javax.swing.GroupLayout(panelCirculo1);
        panelCirculo1.setLayout(panelCirculo1Layout);
        panelCirculo1Layout.setHorizontalGroup(
            panelCirculo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );
        panelCirculo1Layout.setVerticalGroup(
            panelCirculo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(panelCirculo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBoard1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelCirculo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(444, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Vendas");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 8, 0));

        botaoAdicionar.setBackground(new java.awt.Color(0, 0, 70));
        botaoAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.setName("adicionar"); // NOI18N
        botaoAdicionar.addActionListener(this::botaoAdicionarActionPerformed);
        jPanel1.add(botaoAdicionar);

        botaoAtualizar.setBackground(new java.awt.Color(0, 0, 70));
        botaoAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.setName("atualizar"); // NOI18N
        botaoAtualizar.addActionListener(this::botaoAtualizarActionPerformed);
        jPanel1.add(botaoAtualizar);

        botaoRemover.setBackground(new java.awt.Color(0, 0, 70));
        botaoRemover.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoRemover.setForeground(new java.awt.Color(255, 255, 255));
        botaoRemover.setText("Remover");
        botaoRemover.setName("remover"); // NOI18N
        botaoRemover.addActionListener(this::botaoRemoverActionPerformed);
        jPanel1.add(botaoRemover);

        botaoPermissoes.setBackground(new java.awt.Color(0, 0, 70));
        botaoPermissoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoPermissoes.setForeground(new java.awt.Color(255, 255, 255));
        botaoPermissoes.setText("Detalhes");
        botaoPermissoes.setActionCommand("permissoes");
        botaoPermissoes.setName("permissoes"); // NOI18N
        jPanel1.add(botaoPermissoes);
        botaoPermissoes.getAccessibleContext().setAccessibleName("permissoes");

        botaoImprimir.setBackground(new java.awt.Color(0, 0, 70));
        botaoImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoImprimir.setForeground(new java.awt.Color(255, 255, 255));
        botaoImprimir.setText("Imprimir");
        botaoImprimir.setName("imprimir"); // NOI18N
        jPanel1.add(botaoImprimir);

        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaVendas);

        labelDataInicial.setText("Data inicial:");

        labelDataFinal.setText("Data final:");

        botaoPesquisar.setBackground(new java.awt.Color(0, 0, 70));
        botaoPesquisar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.setActionCommand("permissoes");
        botaoPesquisar.setName("permissoes"); // NOI18N
        botaoPesquisar.addActionListener(this::botaoPesquisarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(labelDataInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(labelDataFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDataInicial))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dataFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelDataFinal, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(botaoPesquisar)))))
                    .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoPesquisarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JButton botaoPermissoes;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoRemover;
    private com.toedter.calendar.JDateChooser dataFinal;
    private com.toedter.calendar.JDateChooser dataInicial;
    private javax.swing.JDialog dialogVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDataFinal;
    private javax.swing.JLabel labelDataInicial;
    private com.jotadev.gestao.vendas.visual.componentes.PanelBoard panelBoard1;
    private com.jotadev.gestao.vendas.visual.componentes.PanelCirculo panelCirculo1;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaVendas;
    // End of variables declaration//GEN-END:variables
}
