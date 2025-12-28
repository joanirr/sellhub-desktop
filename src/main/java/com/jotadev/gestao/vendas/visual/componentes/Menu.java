
package com.jotadev.gestao.vendas.visual.componentes;

import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.visual.evento.EventoMenuSelecionado;
import com.jotadev.gestao.vendas.visual.modelo.MenuModelo;
import static com.jotadev.gestao.vendas.visual.modelo.MenuModelo.TipoMenu.MENU;
import static com.jotadev.gestao.vendas.visual.modelo.MenuModelo.TipoMenu.TITULO;
import static com.jotadev.gestao.vendas.visual.modelo.MenuModelo.TipoMenu.VAZIO;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import javax.swing.ImageIcon;

public class Menu extends javax.swing.JPanel {
    
    private EventoMenuSelecionado evento;

    public Menu() {
        initComponents();
        setOpaque(false);
        listaMenu1.setOpaque(false);
        inicializarMenu();
    }
    
    public void addEventoMenu(EventoMenuSelecionado evento) {
        this.listaMenu1.addEventoMenu(evento);
    }
    
    private void inicializarMenu() {
        listaMenu1.adicionarItem(new MenuModelo("1", "Dashboard", MENU));
        listaMenu1.adicionarItem(new MenuModelo("2", "Produto", MENU));
        listaMenu1.adicionarItem(new MenuModelo("3", "Estoque", MENU));
        listaMenu1.adicionarItem(new MenuModelo("4", "Cliente", MENU));
        listaMenu1.adicionarItem(new MenuModelo("5", "Venda", MENU));
        listaMenu1.adicionarItem(new MenuModelo("6", "Usuário", MENU));
        
        listaMenu1.adicionarItem(new MenuModelo("", "", VAZIO));
        listaMenu1.adicionarItem(new MenuModelo("", "Suporte", TITULO));
        listaMenu1.adicionarItem(new MenuModelo("7", "Configuração", MENU));
        listaMenu1.adicionarItem(new MenuModelo("8", "Relatório", MENU));
        listaMenu1.adicionarItem(new MenuModelo("9", "Fale conosco", MENU));
        listaMenu1.adicionarItem(new MenuModelo("10", "Sair", MENU));
    }
    
    public void inicializarFotoDoPerfil(Usuario usuario) {
        labelNomeUsuario.setText(usuario.getNome());
        
        if (usuario.getFoto() != null) {
            try {
                File file = new File (usuario.getFoto());
                if (file.isFile()) {
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    imageAvatar1.setImage(icon);
                }
            } catch (Exception e) {
                System.out.println("Erro ao carregar foto do usuário.");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(47, 95, 106), 0, getHeight(), new Color(25, 55, 65));
        graphics2D.setPaint(gradientPaint);
        
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        graphics2D.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        imageAvatar1 = new com.jotadev.gestao.vendas.visual.componentes.ImageAvatar();
        labelNomeUsuario = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        listaMenu1 = new com.jotadev.gestao.vendas.visual.componentes.ListaMenu<>();

        jPanel1.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\SellHub3.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\avatar.png")); // NOI18N

        labelNomeUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelNomeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNomeUsuario.setText("Joanir Rodrigo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addComponent(labelNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addComponent(listaMenu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNomeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jotadev.gestao.vendas.visual.componentes.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelNomeUsuario;
    private com.jotadev.gestao.vendas.visual.componentes.ListaMenu<String> listaMenu1;
    // End of variables declaration//GEN-END:variables
}
