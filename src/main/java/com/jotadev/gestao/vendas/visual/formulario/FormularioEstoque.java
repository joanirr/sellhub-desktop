package com.jotadev.gestao.vendas.visual.formulario;

import com.jotadev.gestao.vendas.controlador.FormularioEstoqueController;
import com.jotadev.gestao.vendas.modelo.util.MensagemUtil;
import com.jotadev.gestao.vendas.visual.componentes.Botao;
import com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto;
import com.jotadev.gestao.vendas.visual.componentes.Tabela;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import net.miginfocom.swing.MigLayout;

public class FormularioEstoque extends javax.swing.JPanel {
    
    private FormularioEstoqueController formularioEstoqueController;
    private Long usuarioId;
    private MensagemUtil mensagem;
    private MigLayout layout;
    private FormularioPrincipal formularioPrincipal;

    public FormularioEstoque(Long usuarioId, FormularioPrincipal formularioPrincipal) {
        initComponents();
        
        setOpaque(true);
        jScrollPane1.getViewport().setBackground(new Color(45,45,45));
        dialogEstoque.setResizable(false);
        
        this.usuarioId = usuarioId;
        this.formularioPrincipal = formularioPrincipal;
        
        formularioEstoqueController = new FormularioEstoqueController(this);
        
        layout = new MigLayout("fill, insets");
        background.setLayout(layout);
        background.add(panelBoard1);
        mensagem = new MensagemUtil(background, layout);
        
        eventoDosBotoes();
        eventoDoMouse();
        eventoDoTecldao();
    }

    public MensagemUtil getMensagem() {
        return mensagem;
    }

    public FormularioPrincipal getFormularioPrincipal() {
        return formularioPrincipal;
    }
    

    public Long getUsuarioId() {
        return usuarioId;
    }
    
    private void eventoDosBotoes() {
        botaoAtualizar.addActionListener(formularioEstoqueController);
        botaoAdicionar.addActionListener(formularioEstoqueController);
        botaoImprimir.addActionListener(formularioEstoqueController);
        botaoRemover.addActionListener(formularioEstoqueController);
        botaoSalvar.addActionListener(formularioEstoqueController);
    }
    
    private void eventoDoTecldao() {
        textoNomeOuId.addKeyListener(formularioEstoqueController);
    }
    
    private void eventoDoMouse() {
        tabelaEstoque.addMouseListener(formularioEstoqueController);
    }

    public Tabela getTabelaEstoque() {
        return tabelaEstoque;
    }

    public JDialog getDialogEstoque() {
        return dialogEstoque;
    }

    public CampoDeTexto getTextoNomeOuId() {
        return textoNomeOuId;
    }

    public CampoDeTexto getTextoObservacao() {
        return textoObservacao;
    }

    public CampoDeTexto getTextoQuantidade() {
        return textoQuantidade;
    }

    public JRadioButton getRadioAtivar() {
        return radioAtivar;
    }

    public JRadioButton getRadioDesativar() {
        return radioDesativar;
    }

    public Botao getBotaoSalvar() {
        return botaoSalvar;
    }

    public JLabel getLabelTextoDoProduto() {
        return labelTextoDoProduto;
    }
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogEstoque = new javax.swing.JDialog();
        background = new javax.swing.JLayeredPane();
        panelBoard1 = new com.jotadev.gestao.vendas.visual.componentes.PanelBoard();
        jLabel2 = new javax.swing.JLabel();
        textoNomeOuId = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        textoQuantidade = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        textoObservacao = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        labelTextoDoProduto = new javax.swing.JLabel();
        botaoSalvar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        radioAtivar = new javax.swing.JRadioButton();
        radioDesativar = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoAtualizar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoRemover = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoImprimir = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEstoque = new com.jotadev.gestao.vendas.visual.componentes.Tabela();

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        panelBoard1.setCor2(new java.awt.Color(30, 30, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registro de Estoque");

        textoNomeOuId.setCaretColor(new java.awt.Color(255, 255, 255));
        textoNomeOuId.setDicas("Nome ou id do produto");
        textoNomeOuId.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\produto1.png")); // NOI18N
        textoNomeOuId.addActionListener(this::textoNomeOuIdActionPerformed);

        textoQuantidade.setCaretColor(new java.awt.Color(255, 255, 255));
        textoQuantidade.setDicas("Quantidade");
        textoQuantidade.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\quantidade.png")); // NOI18N

        textoObservacao.setCaretColor(new java.awt.Color(255, 255, 255));
        textoObservacao.setDicas("Observações");
        textoObservacao.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\observacao.png")); // NOI18N

        botaoSalvar.setBackground(new java.awt.Color(28, 181, 223));
        botaoSalvar.setText("Salvar");
        botaoSalvar.setActionCommand("salvar");
        botaoSalvar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoSalvar.addActionListener(this::botaoSalvarActionPerformed);

        buttonGroup1.add(radioAtivar);
        radioAtivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioAtivar.setForeground(new java.awt.Color(255, 255, 255));
        radioAtivar.setSelected(true);
        radioAtivar.setText("Ativar");

        buttonGroup1.add(radioDesativar);
        radioDesativar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioDesativar.setForeground(new java.awt.Color(255, 255, 255));
        radioDesativar.setText("Desativar");

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addComponent(textoObservacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoNomeOuId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTextoDoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(radioAtivar)
                        .addGap(48, 48, 48)
                        .addComponent(radioDesativar)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textoNomeOuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textoObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioAtivar)
                    .addComponent(radioDesativar))
                .addGap(18, 18, 18)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTextoDoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogEstoqueLayout = new javax.swing.GroupLayout(dialogEstoque.getContentPane());
        dialogEstoque.getContentPane().setLayout(dialogEstoqueLayout);
        dialogEstoqueLayout.setHorizontalGroup(
            dialogEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
            .addGroup(dialogEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dialogEstoqueLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        dialogEstoqueLayout.setVerticalGroup(
            dialogEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
            .addGroup(dialogEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dialogEstoqueLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setBackground(new java.awt.Color(45, 45, 45));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Estoque");

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
        botaoImprimir.setName("imprimir"); // NOI18N
        jPanel1.add(botaoImprimir);

        tabelaEstoque.setBackground(new java.awt.Color(45, 45, 45));
        tabelaEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaEstoque);

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

    private void textoNomeOuIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNomeOuIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNomeOuIdActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JButton botaoRemover;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JDialog dialogEstoque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTextoDoProduto;
    private com.jotadev.gestao.vendas.visual.componentes.PanelBoard panelBoard1;
    private javax.swing.JRadioButton radioAtivar;
    private javax.swing.JRadioButton radioDesativar;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaEstoque;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoNomeOuId;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoObservacao;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoQuantidade;
    // End of variables declaration//GEN-END:variables
}
