package com.jotadev.gestao.vendas.visual.formulario;

import com.jotadev.gestao.vendas.controlador.FormularioUsuarioController;
import com.jotadev.gestao.vendas.visual.componentes.ModernScrollBarUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FormularioUsuario extends javax.swing.JPanel {
    
    private FormularioUsuarioController formularioUsuarioController;
    private Long usuarioId;

    public FormularioUsuario(Long usuarioId) {
        initComponents();
        
        setOpaque(true);
        jScrollPane1.getViewport().setBackground(new Color(45,45,45));
        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jDialog2.setResizable(false);
        dialogUsuario.setResizable(false);
        txtFoto.setColumns(15);
        
        this.usuarioId = usuarioId;
        
        formularioUsuarioController = new FormularioUsuarioController(this);
        eventoDosBotoes();
        eventoDoMouse();
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    
    private void eventoDosBotoes() {
        botaoAtualizar.addActionListener(formularioUsuarioController);
        botaoAdicionar.addActionListener(formularioUsuarioController);
        botaoSelecionarArquivo.addActionListener(formularioUsuarioController);
        botaoImprimir.addActionListener(formularioUsuarioController);
        botaoPermissoes.addActionListener(formularioUsuarioController);
        botaoRemover.addActionListener(formularioUsuarioController);
        botaoUsuarioCadastro.addActionListener(formularioUsuarioController);
        botaoUsuarioPermissao.addActionListener(formularioUsuarioController);
    }
    
    private void eventoDoMouse() {
        tabelaUsuarios.addMouseListener(formularioUsuarioController);
    }
    
    public JDialog getDialogUsuario() {
        return dialogUsuario;
    }
    
    public JDialog getDialogPermissao() {
        return jDialog2;
    }
    
    public JTable getTabelaUsuarios() {
        return tabelaUsuarios;
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
    
    public JButton getBotaoSelecionarArquivo() {
        return botaoSelecionarArquivo;
    }
    
    public JButton getBotaoSalvarUsuario() {
        return botaoUsuarioCadastro;
    }
    
    public JButton getBotaoSalvarPermissao() {
        return botaoUsuarioPermissao;
    }
    
    public JLabel getLabelTitulo() {
        return labelTitulo;
    }
    
    public JLabel getLabelPermissaoUsuario() {
        return labelPermissaoUsuario;
    }
    
    public JLabel getLabelMensagem() {
        return labelMensagem;
    }
    
    public JTextField getTxtNome() {
        return txtNome;
    }
    
    public JTextField getTxtEmail() {
        return txtEmail;
    }
    
    public JTextField getTxtSenha() {
        return txtSenha;
    }
    
    public JTextField getTxtFoto() {
        return txtFoto;
    }
    
    public JComboBox getComboPerfil() {
        return comboPerfil;
    }
    
    public JRadioButton getRadioAtivo() {
        return radioAtivo;
    }
    
    public JRadioButton getRadioInativo() {
        return radioInativo;
    }
    
    public List<JCheckBox> listaChecks() {
        List<JCheckBox> lista = new ArrayList<>();
        
        lista.add(jCheckBox1);
        lista.add(jCheckBox2);
        lista.add(jCheckBox3);
        lista.add(jCheckBox4);
        lista.add(jCheckBox5);
        lista.add(jCheckBox6);
        lista.add(jCheckBox7);
        lista.add(jCheckBox8);
        lista.add(jCheckBox9);
        lista.add(jCheckBox10);
        lista.add(jCheckBox11);
        lista.add(jCheckBox12);
        lista.add(jCheckBox13);
        lista.add(jCheckBox14);
        lista.add(jCheckBox15);
        lista.add(jCheckBox16);
        lista.add(jCheckBox17);
        
        return lista;
    }
    
    public JCheckBox getCheckBox1() {
        return jCheckBox1;
    }
    
    public JCheckBox getCheckBox2() {
        return jCheckBox2;
    }
    
    public JCheckBox getCheckBox3() {
        return jCheckBox3;
    }
    
    public JCheckBox getCheckBox4() {
        return jCheckBox4;
    }
    
    public JCheckBox getCheckBox5() {
        return jCheckBox5;
    }
    
    public JCheckBox getCheckBox6() {
        return jCheckBox6;
    }
    
    public JCheckBox getCheckBox7() {
        return jCheckBox7;
    }
    
    public JCheckBox getCheckBox8() {
        return jCheckBox8;
    }
    
    public JCheckBox getCheckBox9() {
        return jCheckBox9;
    }
    
    public JCheckBox getCheckBox10() {
        return jCheckBox10;
    }
    
    public JCheckBox getCheckBox11() {
        return jCheckBox11;
    }
    
    public JCheckBox getCheckBox12() {
        return jCheckBox12;
    }
    
    public JCheckBox getCheckBox13() {
        return jCheckBox13;
    }
    
    public JCheckBox getCheckBox14() {
        return jCheckBox14;
    }
    
    public JCheckBox getCheckBox15() {
        return jCheckBox15;
    }
    
    public JCheckBox getCheckBox16() {
        return jCheckBox16;
    }
    
    public JCheckBox getCheckBox17() {
        return jCheckBox17;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogUsuario = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        comboPerfil = new javax.swing.JComboBox<>();
        txtFoto = new javax.swing.JTextField();
        radioAtivo = new javax.swing.JRadioButton();
        radioInativo = new javax.swing.JRadioButton();
        txtSenha = new javax.swing.JPasswordField();
        botaoSelecionarArquivo = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoUsuarioCadastro = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jDialog2 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        labelPermissaoUsuario = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        botaoUsuarioPermissao = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBoard1 = new com.jotadev.gestao.vendas.visual.componentes.PanelBoard();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoAtualizar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoRemover = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoPermissoes = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoImprimir = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuarios = new com.jotadev.gestao.vendas.visual.componentes.Tabela();

        jPanel2.setBackground(new java.awt.Color(30, 33, 35));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(200, 200, 200));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("ADICIONAR USUÁRIO");
        labelTitulo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(200, 200, 200));
        jLabel4.setText("Nome:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(200, 200, 200));
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(200, 200, 200));
        jLabel6.setText("Senha:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(200, 200, 200));
        jLabel7.setText("Perfil:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(200, 200, 200));
        jLabel8.setText("Foto:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(200, 200, 200));
        jLabel9.setText("Estado:");

        txtNome.setBackground(new java.awt.Color(0, 22, 23));
        txtNome.setForeground(new java.awt.Color(255, 255, 255));
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 70, 65)));
        txtNome.setSelectionColor(new java.awt.Color(61, 144, 105));
        txtNome.addActionListener(this::txtNomeActionPerformed);

        txtEmail.setBackground(new java.awt.Color(0, 22, 23));
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 70, 65)));
        txtEmail.setSelectionColor(new java.awt.Color(61, 144, 105));

        comboPerfil.setBackground(new java.awt.Color(0, 22, 23));
        comboPerfil.setForeground(new java.awt.Color(200, 200, 200));
        comboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Admin", "Padrão" }));
        comboPerfil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 70, 65)));

        txtFoto.setBackground(new java.awt.Color(0, 22, 23));
        txtFoto.setForeground(new java.awt.Color(40, 75, 100));
        txtFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 70, 65)));
        txtFoto.setEnabled(false);
        txtFoto.setFocusable(false);

        radioAtivo.setBackground(new java.awt.Color(45, 45, 45));
        buttonGroup1.add(radioAtivo);
        radioAtivo.setForeground(new java.awt.Color(200, 200, 200));
        radioAtivo.setSelected(true);
        radioAtivo.setText("Ativo");
        radioAtivo.setContentAreaFilled(false);

        radioInativo.setBackground(new java.awt.Color(45, 45, 45));
        buttonGroup1.add(radioInativo);
        radioInativo.setForeground(new java.awt.Color(200, 200, 200));
        radioInativo.setText("Inativo");
        radioInativo.setContentAreaFilled(false);

        txtSenha.setBackground(new java.awt.Color(0, 22, 23));
        txtSenha.setForeground(new java.awt.Color(255, 255, 255));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 70, 65)));
        txtSenha.setSelectionColor(new java.awt.Color(61, 144, 105));

        botaoSelecionarArquivo.setText("Selecionar");
        botaoSelecionarArquivo.setActionCommand("selecionar");
        botaoSelecionarArquivo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        botaoSelecionarArquivo.addActionListener(this::botaoSelecionarArquivoActionPerformed);

        botaoUsuarioCadastro.setText("Salvar");
        botaoUsuarioCadastro.setActionCommand("salvar");
        botaoUsuarioCadastro.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoUsuarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoSelecionarArquivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(radioAtivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                .addComponent(radioInativo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNome)
                            .addComponent(txtEmail)
                            .addComponent(comboPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSenha))))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoSelecionarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(radioAtivo)
                    .addComponent(radioInativo))
                .addGap(18, 18, 18)
                .addComponent(botaoUsuarioCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogUsuarioLayout = new javax.swing.GroupLayout(dialogUsuario.getContentPane());
        dialogUsuario.getContentPane().setLayout(dialogUsuarioLayout);
        dialogUsuarioLayout.setHorizontalGroup(
            dialogUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogUsuarioLayout.setVerticalGroup(
            dialogUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog2.setBackground(new java.awt.Color(45, 45, 45));

        jPanel3.setBackground(new java.awt.Color(30, 33, 35));
        jPanel3.setForeground(new java.awt.Color(176, 172, 178));

        labelPermissaoUsuario.setBackground(new java.awt.Color(45, 45, 45));
        labelPermissaoUsuario.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        labelPermissaoUsuario.setForeground(new java.awt.Color(200, 200, 200));
        labelPermissaoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPermissaoUsuario.setText("Joanir Rodrigo");

        jCheckBox1.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox1.setText("Salvar");
        jCheckBox1.setName("usuario:salvar"); // NOI18N
        jCheckBox1.addActionListener(this::jCheckBox1ActionPerformed);

        jCheckBox2.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox2.setText("Somente seus dados");
        jCheckBox2.setName("usuario:somente_seu"); // NOI18N

        jCheckBox3.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox3.setText("Buscar todos");
        jCheckBox3.setName("usuario:buscar_todos"); // NOI18N

        jCheckBox4.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox4.setText("Remover");
        jCheckBox4.setName("usuario:remover"); // NOI18N

        jCheckBox5.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox5.setText("Todos os históricos");
        jCheckBox5.setName("estoque:todos_historicos"); // NOI18N

        jCheckBox6.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox6.setText("Remover");
        jCheckBox6.setName("estoque:remover"); // NOI18N
        jCheckBox6.addActionListener(this::jCheckBox6ActionPerformed);

        jCheckBox7.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox7.setText("Salvar");
        jCheckBox7.setName("estoque:salvar"); // NOI18N

        jCheckBox8.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox8.setText("Somente seu historico");
        jCheckBox8.setName("estoque:seu_historico"); // NOI18N

        jCheckBox9.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox9.setText("Salvar");
        jCheckBox9.setName("produto:salvar"); // NOI18N

        jCheckBox10.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox10.setText("Remover");
        jCheckBox10.setName("produto:remover"); // NOI18N
        jCheckBox10.addActionListener(this::jCheckBox10ActionPerformed);

        jCheckBox11.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox11.setText("Salvar");
        jCheckBox11.setName("categoria:salvar"); // NOI18N

        jCheckBox12.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox12.setText("Remover");
        jCheckBox12.setName("categoria:remover"); // NOI18N

        jCheckBox13.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox13.setText("Salvar");
        jCheckBox13.setName("venda:salvar"); // NOI18N
        jCheckBox13.addActionListener(this::jCheckBox13ActionPerformed);

        jCheckBox16.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox16.setText("Salvar");
        jCheckBox16.setName("permissao:salvar"); // NOI18N

        jCheckBox17.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox17.setText("Remover");
        jCheckBox17.setName("cliente:remover"); // NOI18N

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(200, 200, 200));
        jLabel11.setText("Usuário");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(200, 200, 200));
        jLabel12.setText("Estoque");

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(200, 200, 200));
        jLabel13.setText("Produto");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(200, 200, 200));
        jLabel14.setText("Categoria");

        jSeparator3.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(200, 200, 200));
        jLabel15.setText("Venda");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(200, 200, 200));
        jLabel16.setText("Permissão");

        jSeparator4.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(200, 200, 200));
        jLabel17.setText("Cliente");

        botaoUsuarioPermissao.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        botaoUsuarioPermissao.setText("Salvar");
        botaoUsuarioPermissao.setActionCommand("salvarpermissao");
        botaoUsuarioPermissao.setName("salvarpermissao"); // NOI18N
        botaoUsuarioPermissao.addActionListener(this::botaoUsuarioPermissaoActionPerformed);

        jCheckBox14.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox14.setText("Atualizar");
        jCheckBox14.setName("venda:atualizar"); // NOI18N

        jCheckBox15.setForeground(new java.awt.Color(176, 172, 178));
        jCheckBox15.setText("Remover");
        jCheckBox15.setName("venda:remover"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox10)
                            .addComponent(jCheckBox9)
                            .addComponent(jCheckBox13)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jCheckBox14)
                            .addComponent(jCheckBox15)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox8)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox6)
                            .addComponent(jCheckBox7)
                            .addComponent(jLabel14))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(144, 144, 144))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox11)
                            .addComponent(jCheckBox12))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoUsuarioPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jCheckBox17))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPermissaoUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jCheckBox16))
                        .addGap(134, 134, 134)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(labelPermissaoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox4)
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox9)
                                    .addComponent(jCheckBox11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox10)
                                    .addComponent(jCheckBox12))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox8)
                                .addGap(43, 43, 43)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox13)
                            .addComponent(jCheckBox16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox15)
                .addGap(25, 25, 25)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox17)
                .addGap(18, 18, 18)
                .addComponent(botaoUsuarioPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        botaoUsuarioPermissao.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(45, 45, 45));
        setEnabled(false);
        setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Usuário");

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
        botaoRemover.setName("remover"); // NOI18N
        botaoRemover.addActionListener(this::botaoRemoverActionPerformed);
        jPanel1.add(botaoRemover);

        botaoPermissoes.setBackground(new java.awt.Color(25, 55, 65));
        botaoPermissoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoPermissoes.setForeground(new java.awt.Color(255, 255, 255));
        botaoPermissoes.setText("Permissões");
        botaoPermissoes.setActionCommand("permissoes");
        botaoPermissoes.setName("permissoes"); // NOI18N
        jPanel1.add(botaoPermissoes);
        botaoPermissoes.getAccessibleContext().setAccessibleName("");

        botaoImprimir.setBackground(new java.awt.Color(25, 55, 65));
        botaoImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoImprimir.setForeground(new java.awt.Color(255, 255, 255));
        botaoImprimir.setText("Imprimir");
        botaoImprimir.setName("imprimir"); // NOI18N
        jPanel1.add(botaoImprimir);

        jScrollPane1.setBackground(new java.awt.Color(25, 25, 25));

        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaUsuarios);

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

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void botaoUsuarioPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoUsuarioPermissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoUsuarioPermissaoActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox10ActionPerformed

    private void jCheckBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox13ActionPerformed

    private void botaoSelecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSelecionarArquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoSelecionarArquivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JButton botaoPermissoes;
    private javax.swing.JButton botaoRemover;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoSelecionarArquivo;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoUsuarioCadastro;
    private javax.swing.JButton botaoUsuarioPermissao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboPerfil;
    private javax.swing.JDialog dialogUsuario;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel labelPermissaoUsuario;
    private javax.swing.JLabel labelTitulo;
    private com.jotadev.gestao.vendas.visual.componentes.PanelBoard panelBoard1;
    private javax.swing.JRadioButton radioAtivo;
    private javax.swing.JRadioButton radioInativo;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaUsuarios;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
