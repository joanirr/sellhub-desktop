package com.jotadev.gestao.vendas.visual.formulario;

import com.jotadev.gestao.vendas.controlador.LoginControlador;
import com.jotadev.gestao.vendas.modelo.util.MensagemUtil;
import com.jotadev.gestao.vendas.visual.componentes.Botao;
import com.jotadev.gestao.vendas.visual.componentes.CampoDeSenha;
import com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto;
import com.jotadev.gestao.vendas.visual.componentes.PanelCarregar;
import com.jotadev.gestao.vendas.modelo.util.MensagemUtil;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

public class Login extends javax.swing.JFrame {
    
    private MigLayout layout;
    private PanelCarregar panelCarregar;
    private MensagemUtil mensagemUtil;
    private LoginControlador loginControlador;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        loginControlador = new LoginControlador(this);
        
        layout = new MigLayout("fill, insets");
        panelCarregar = new PanelCarregar();
        
        background.setLayout(layout);
        background.add(panelCarregar, "pos 0 0 100% 100%");
        background.add(panelBoard1, "pos 0 0 100% 100%");
        
        mensagemUtil = new MensagemUtil(background, layout);
        evento();
    }
    
    private void evento() {
        botaoLogin.addActionListener(loginControlador);
    }

    public MensagemUtil getMensagemUtil() {
        return mensagemUtil;
    }
    

    public PanelCarregar getPanelCarregar() {
        return panelCarregar;
    }

    public CampoDeTexto getCampoDeTextoEmail() {
        return campoDeTextoEmail;
    }

    public Botao getBotaoLogin() {
        return botaoLogin;
    }
    
    public CampoDeSenha getCampoDeSenha() {
        return campoDeSenha;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBoard1 = new com.jotadev.gestao.vendas.visual.componentes.PanelBoard();
        campoDeSenha = new com.jotadev.gestao.vendas.visual.componentes.CampoDeSenha();
        campoDeTextoEmail = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        botaoLogin = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        background = new javax.swing.JLayeredPane();

        panelBoard1.setCor1(new java.awt.Color(255, 255, 255));
        panelBoard1.setCor2(new java.awt.Color(102, 159, 210));

        campoDeSenha.setCor(new java.awt.Color(255, 255, 255));
        campoDeSenha.setDicas("Senha");
        campoDeSenha.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\pass.png")); // NOI18N
        campoDeSenha.addActionListener(this::campoDeSenhaActionPerformed);

        campoDeTextoEmail.setCor(new java.awt.Color(255, 255, 255));
        campoDeTextoEmail.setDicas("Email");
        campoDeTextoEmail.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\mail.png")); // NOI18N

        botaoLogin.setBackground(new java.awt.Color(28, 181, 224));
        botaoLogin.setForeground(new java.awt.Color(255, 255, 255));
        botaoLogin.setIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\login_16.png")); // NOI18N
        botaoLogin.setText("LOGIN");
        botaoLogin.setActionCommand("login");
        botaoLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        botaoLogin.addActionListener(this::botaoLoginActionPerformed);

        jPanel1.setOpaque(false);

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\SellHub.png")); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoDeSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(campoDeTextoEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard1Layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(campoDeTextoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoDeSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 54, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoDeSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDeSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDeSenhaActionPerformed

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoLogin;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeSenha campoDeSenha;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto campoDeTextoEmail;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private com.jotadev.gestao.vendas.visual.componentes.PanelBoard panelBoard1;
    // End of variables declaration//GEN-END:variables
}
