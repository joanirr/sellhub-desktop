package com.jotadev.gestao.vendas.visual.formulario;

import com.jotadev.gestao.vendas.visual.componentes.ModernScrollBarUI;
import com.jotadev.gestao.vendas.controlador.FormularioPrincipalController;
import com.jotadev.gestao.vendas.visual.componentes.Tabela;
import com.jotadev.gestao.vendas.visual.modelo.CartaoModelo;
import java.awt.Color;
import javax.swing.ImageIcon;

public class FormularioPrincipal extends javax.swing.JPanel {
    
    public FormularioPrincipal() {
        initComponents();
        inicializarCartao();
        
        FormularioPrincipalController controller = new FormularioPrincipalController(this);
        controller.atualizarDash();
        
        jScrollPane1.getViewport().setBackground(new Color(45, 45, 45));
        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPane1.setBackground(new Color(45, 45, 45));
        jScrollPane1.setBorder(null);
        
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {
                new FormularioPrincipalController(FormularioPrincipal.this).atualizarDash();
            }
        });
    }
    
    public void atualizarDadosCartoes(long totalProdutos, long totalVendas, long totalEstoque) {
        CartaoModelo m1 = new CartaoModelo(new ImageIcon(getCaminho() + "produto.png"), "Produto", "Total " + totalProdutos);
        CartaoModelo m2 = new CartaoModelo(new ImageIcon(getCaminho() + "venda.png"), "Venda", "Total " + totalVendas);
        CartaoModelo m3 = new CartaoModelo(new ImageIcon(getCaminho() + "stock.png"), "Estoque", "Total " + totalEstoque);

        cartao1.setData(m1);
        cartao2.setData(m2);
        cartao3.setData(m3);
    }

    private void inicializarCartao() {
        atualizarDadosCartoes(0, 0, 0); 
    }

    public Tabela getTabelaDoFormularioPrincipal() {
        return tabelaDoFormularioPrincipal;
    }
    
    
    private String getCaminho() {
        return System.getProperty("user.dir") + "\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\";
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cartao1 = new com.jotadev.gestao.vendas.visual.componentes.Cartao();
        cartao2 = new com.jotadev.gestao.vendas.visual.componentes.Cartao();
        cartao3 = new com.jotadev.gestao.vendas.visual.componentes.Cartao();
        panelBoard1 = new com.jotadev.gestao.vendas.visual.componentes.PanelBoard();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDoFormularioPrincipal = new com.jotadev.gestao.vendas.visual.componentes.Tabela();

        setBackground(new java.awt.Color(45, 45, 45));

        jPanel1.setBackground(new java.awt.Color(45, 45, 45));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cartao1.setCor1(new java.awt.Color(29, 185, 84));
        cartao1.setCor2(new java.awt.Color(18, 18, 18));
        jPanel1.add(cartao1);

        cartao2.setCor1(new java.awt.Color(18, 18, 18));
        cartao2.setCor2(new java.awt.Color(61, 90, 128));
        jPanel1.add(cartao2);

        cartao3.setCor1(new java.awt.Color(131, 56, 236));
        cartao3.setCor2(new java.awt.Color(18, 18, 18));
        jPanel1.add(cartao3);

        panelBoard1.setCor1(new java.awt.Color(45, 45, 45));
        panelBoard1.setCor2(new java.awt.Color(45, 45, 45));

        jLabel1.setBackground(new java.awt.Color(242, 242, 242));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(130, 130, 130));
        jLabel1.setText("Histórico do estoque");

        tabelaDoFormularioPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Quantidade", "Estado", "Motivo", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaDoFormularioPrincipal);

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jotadev.gestao.vendas.visual.componentes.Cartao cartao1;
    private com.jotadev.gestao.vendas.visual.componentes.Cartao cartao2;
    private com.jotadev.gestao.vendas.visual.componentes.Cartao cartao3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.jotadev.gestao.vendas.visual.componentes.PanelBoard panelBoard1;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaDoFormularioPrincipal;
    // End of variables declaration//GEN-END:variables
}
