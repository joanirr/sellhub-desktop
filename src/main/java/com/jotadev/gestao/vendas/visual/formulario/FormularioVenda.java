package com.jotadev.gestao.vendas.visual.formulario;

import com.jotadev.gestao.vendas.controlador.FormularioVendaController;
import com.jotadev.gestao.vendas.modelo.util.MensagemUtil;
import com.jotadev.gestao.vendas.visual.componentes.Botao;
import com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto;
import com.jotadev.gestao.vendas.visual.componentes.ComboBox;
import com.jotadev.gestao.vendas.visual.componentes.ModernScrollBarUI;
import com.jotadev.gestao.vendas.visual.componentes.Tabela;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.text.JTextComponent;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;



public class FormularioVenda extends javax.swing.JPanel {
    
    private FormularioVendaController formularioVendaController;
    private Long usuarioId;
    private MigLayout layout;
    private MensagemUtil mensagemUtil;
    private boolean mostrar = true;

    public FormularioVenda(Long usuarioId) {
        initComponents();
        
        jScrollPane4.getViewport().setBackground(new Color(45,45,45));
        tabelaCheckout.setBackground(new Color(45,45,45));
        tabelaCheckout.setFocusable(false);
        tabelaCheckout.setCellSelectionEnabled(false);
        tabelaCheckout.setRowSelectionAllowed(true);
        tabelaCheckout.setShowGrid(false);
        
        tabelaVendas.setFocusable(false);
        tabelaVendas.setCellSelectionEnabled(false);
        tabelaVendas.setRowSelectionAllowed(true);
        tabelaVendas.setShowGrid(false);
        
        layout = new MigLayout("fill, insets");
        background.setLayout(layout);
        background.add(panelBoard1);
        
        this.mensagemUtil = new MensagemUtil(background, layout);
        
        Border bordaEscura = BorderFactory.createLineBorder(new Color(70, 70, 70));
        
        dataInicial.getComponent(1).setBackground(new Color(45, 45, 45));
        dataInicial.getComponent(1).setForeground(Color.WHITE);
        ((JComponent) dataInicial.getComponent(1)).setBorder(bordaEscura);
        
        ((JTextComponent) dataInicial.getComponent(1)).setCaretColor(Color.WHITE);
        
        dataFinal.getComponent(1).setBackground(new Color(45, 45, 45));
        dataFinal.getComponent(1).setForeground(Color.WHITE);
        ((JComponent)dataFinal.getComponent(1)).setBorder(bordaEscura);
        ((JTextComponent)dataFinal.getComponent(1)).setCaretColor(Color.WHITE);
        
        
        setOpaque(true);
        jScrollPane1.getViewport().setBackground(new Color(45,45,45));
        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        dialogVenda.setResizable(false);
        
        this.usuarioId = usuarioId;
        
        inicializacaoDoPanelCirculo();
        
        formularioVendaController = new FormularioVendaController(this);
        eventoDosBotoes();
        eventoDoMouse();
        
        getTextoQuantidade().getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void update() {
                formularioVendaController.atualizarCalculos();
            }
        });

        getTextoDesconto().getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            public void update() {
                formularioVendaController.atualizarCalculos();
            }
        });
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
    
    private void inicializacaoDoPanelCirculo() {
        panelCirculo1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        panelCirculo1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrar = !mostrar;
                mostrarCarrinho();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelCirculo1.setBackground(new Color(13,27,42));            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panelCirculo1.setBackground(new Color(40,75,100));
            }
            
        });
    }
    public abstract class DocumentAdapter implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            update();
        }

        public abstract void update();
    }
    
    
    private void eventoDoMouse() {
//        tabelaVendas.addMouseListener(formularioUsuarioController);
    }
    
    public JDialog getDialogUsuario() {
        return dialogVenda;
    }
    
    public JTable getTabelaVendas() {
        return tabelaVendas;
    }

    public Tabela getTabelaCheckout() {
        return tabelaCheckout;
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

    public CampoDeTexto getTextoBuscarProdutoPeloID() {
        return textoBuscarProdutoPeloID;
    }

    public CampoDeTexto getTextoCPF() {
        return textoCPF;
    }

    public CampoDeTexto getTextoDesconto() {
        return textoDesconto;
    }

    public CampoDeTexto getTextoQuantidade() {
        return textoQuantidade;
    }

    public CampoDeTexto getTextoValorPago() {
        return textoValorPago;
    }

    public JLabel getLabelDesconto() {
        return labelDesconto;
    }

    public JLabel getLabelEstoqueQuantidade() {
        return labelEstoqueQuantidade;
    }

    public JLabel getLabelNomeDoProduto() {
        return labelNomeDoProduto;
    }

    public JLabel getLabelPrecoProduto() {
        return labelPrecoProduto;
    }

    public JLabel getLabelTotalVenda() {
        return labelTotalVenda;
    }

    public JLabel getLabelTroco() {
        return labelTroco;
    }

    public JLabel getLabelValorPago() {
        return labelValorPago;
    }

    public Botao getBotaoCarrinhoLimpar() {
        return botaoCarrinhoLimpar;
    }

    public Botao getBotaoAdicionarCarrinho() {
        return botaoAdicionarCarrinho;
    }

    public Botao getBotaoCarrinhoRemover() {
        return botaoCarrinhoRemover;
    }

    public Botao getBotaoLimpar() {
        return botaoLimpar;
    }

    public JButton getBotaoPesquisar() {
        return botaoPesquisar;
    }

    public Botao getBotaoVender() {
        return botaoVender;
    }

    public ComboBox getComboBoxCategoria() {
        return comboBoxCategoria;
    }

    public ComboBox getComboBoxProduto() {
        return comboBoxProduto;
    }

    public MensagemUtil getMensagemUtil() {
        return mensagemUtil;
    }

    public JLabel getLabelCarrinho() {
        return labelCarrinho;
    }

    public JLabel getLabelSubtotalItem() {
        return labelSubtotalItem;
    }

    public JDateChooser getDataInicial() {
        return dataInicial;
    }

    public JDateChooser getDataFinal() {
        return dataFinal;
    }
    
    
    
    public void mostrarCarrinho() {
        TimingTarget target = new TimingTarget() {
           @Override
           public void begin() {
               if (!mostrar) {
                   background.add(panelBoard2, "pos 0.5al 240", 0); // adicionar no primeiro índice
                   panelBoard2.setVisible(true);
                   background.repaint();
               }
           }
           
           @Override
           public void timingEvent(float fraction) {
               float f;
               
               if (mostrar) {
                   f = 240 * (1f - fraction);
               } else {
                   f = 240 * fraction;
               }
               
               layout.setComponentConstraints(panelBoard2, "pos 0.5al " + (int) (f - 240));
               background.repaint();
               background.revalidate();
           }
           
           @Override
           public void end() {
               if (mostrar) {
                   background.remove(panelBoard2);
                   background.repaint();
                   background.revalidate();
               } else {
                   mostrar = false;
               }
           }

            @Override
            public void repeat() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogVenda = new javax.swing.JDialog();
        background = new javax.swing.JLayeredPane();
        panelBoard1 = new com.jotadev.gestao.vendas.visual.componentes.PanelBoard();
        jLabel2 = new javax.swing.JLabel();
        panelCirculo1 = new com.jotadev.gestao.vendas.visual.componentes.PanelCirculo();
        labelCarrinho = new javax.swing.JLabel();
        textoBuscarProdutoPeloID = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        textoQuantidade = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        comboBoxCategoria = new com.jotadev.gestao.vendas.visual.componentes.ComboBox();
        comboBoxProduto = new com.jotadev.gestao.vendas.visual.componentes.ComboBox();
        jLabel4 = new javax.swing.JLabel();
        textoDesconto = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        textoValorPago = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        textoCPF = new com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto();
        jPanel2 = new javax.swing.JPanel();
        botaoAdicionarCarrinho = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoVender = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoLimpar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        labelNomeDoProduto = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelEstoqueQuantidade = new javax.swing.JLabel();
        labelPrecoProduto = new javax.swing.JLabel();
        labelTotalVenda = new javax.swing.JLabel();
        labelValorPago = new javax.swing.JLabel();
        labelDesconto = new javax.swing.JLabel();
        labelTroco = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelSubtotalItem = new javax.swing.JLabel();
        panelBoard2 = new com.jotadev.gestao.vendas.visual.componentes.PanelBoard();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        botaoCarrinhoRemover = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoCarrinhoLimpar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaCheckout = new com.jotadev.gestao.vendas.visual.componentes.Tabela();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabela1 = new com.jotadev.gestao.vendas.visual.componentes.Tabela();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoAtualizar = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoRemover = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoPermissoes = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        botaoImprimir = new com.jotadev.gestao.vendas.visual.componentes.Botao();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new com.jotadev.gestao.vendas.visual.componentes.Tabela();
        labelDataInicial = new javax.swing.JLabel();
        dataInicial = new com.toedter.calendar.JDateChooser();
        labelDataFinal = new javax.swing.JLabel();
        dataFinal = new com.toedter.calendar.JDateChooser();
        botaoPesquisar = new com.jotadev.gestao.vendas.visual.componentes.Botao();

        dialogVenda.setBackground(new java.awt.Color(45, 45, 45));

        background.setBackground(new java.awt.Color(0, 35, 25));
        background.setEnabled(false);
        background.setFocusable(false);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        panelBoard1.setBackground(new java.awt.Color(0, 35, 25));
        panelBoard1.setCor2(new java.awt.Color(30, 30, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registro da venda");

        panelCirculo1.setBackground(new java.awt.Color(18, 25, 23));
        panelCirculo1.setPreferredSize(new java.awt.Dimension(65, 65));

        labelCarrinho.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelCarrinho.setForeground(new java.awt.Color(255, 255, 255));
        labelCarrinho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCarrinho.setIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\carrinho.png")); // NOI18N
        labelCarrinho.setText("0");

        javax.swing.GroupLayout panelCirculo1Layout = new javax.swing.GroupLayout(panelCirculo1);
        panelCirculo1.setLayout(panelCirculo1Layout);
        panelCirculo1Layout.setHorizontalGroup(
            panelCirculo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCirculo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCarrinho, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCirculo1Layout.setVerticalGroup(
            panelCirculo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCirculo1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labelCarrinho)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        textoBuscarProdutoPeloID.setBackground(new java.awt.Color(45, 55, 52));
        textoBuscarProdutoPeloID.setForeground(new java.awt.Color(220, 220, 220));
        textoBuscarProdutoPeloID.setDicas("Buscar produto pelo ID");
        textoBuscarProdutoPeloID.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\produto1.png")); // NOI18N

        textoQuantidade.setBackground(new java.awt.Color(45, 55, 52));
        textoQuantidade.setForeground(new java.awt.Color(220, 220, 220));
        textoQuantidade.setDicas("Quantidade");
        textoQuantidade.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\quantidade.png")); // NOI18N

        comboBoxCategoria.setBackground(new java.awt.Color(45, 55, 52));
        comboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione a categoria" }));
        comboBoxCategoria.addActionListener(this::comboBoxCategoriaActionPerformed);

        comboBoxProduto.setBackground(new java.awt.Color(45, 55, 52));
        comboBoxProduto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione o produto" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Detalhes da venda");

        textoDesconto.setBackground(new java.awt.Color(45, 55, 52));
        textoDesconto.setForeground(new java.awt.Color(220, 220, 220));
        textoDesconto.setDicas("Desconto");
        textoDesconto.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\desconto.png")); // NOI18N

        textoValorPago.setBackground(new java.awt.Color(45, 55, 52));
        textoValorPago.setForeground(new java.awt.Color(220, 220, 220));
        textoValorPago.setDicas("Valor pago");
        textoValorPago.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\preco.png")); // NOI18N

        textoCPF.setBackground(new java.awt.Color(45, 55, 52));
        textoCPF.setForeground(new java.awt.Color(220, 220, 220));
        textoCPF.setDicas("CPF/CNPJ");
        textoCPF.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\Joanir\\Documents\\NetBeansProjects\\gestao.vendas\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\id.png")); // NOI18N

        jPanel2.setBackground(new java.awt.Color(45, 45, 45));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        botaoAdicionarCarrinho.setBackground(new java.awt.Color(46, 204, 113));
        botaoAdicionarCarrinho.setText("Adicionar");
        botaoAdicionarCarrinho.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(botaoAdicionarCarrinho);

        botaoVender.setText("Vender");
        botaoVender.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(botaoVender);

        botaoLimpar.setBackground(new java.awt.Color(243, 156, 18));
        botaoLimpar.setText("Limpar");
        botaoLimpar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(botaoLimpar);

        jScrollPane2.setViewportView(jList1);

        jPanel3.setBackground(new java.awt.Color(18, 25, 23));

        labelNomeDoProduto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelNomeDoProduto.setForeground(new java.awt.Color(255, 255, 255));
        labelNomeDoProduto.setText("Nome do produto:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(179, 179, 179));
        jLabel6.setText("Qtd. estoque:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(179, 179, 179));
        jLabel7.setText("Preço:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(34, 211, 238));
        jLabel8.setText("Total da venda:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(179, 179, 179));
        jLabel9.setText("Valor pago:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(179, 179, 179));
        jLabel10.setText("Desconto:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(179, 179, 179));
        jLabel11.setText("Troco:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

        labelEstoqueQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelEstoqueQuantidade.setForeground(new java.awt.Color(179, 179, 179));
        labelEstoqueQuantidade.setText("0");

        labelPrecoProduto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelPrecoProduto.setForeground(new java.awt.Color(179, 179, 179));
        labelPrecoProduto.setText("0.00");

        labelTotalVenda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTotalVenda.setForeground(new java.awt.Color(34, 211, 238));
        labelTotalVenda.setText("0.00");

        labelValorPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelValorPago.setForeground(new java.awt.Color(179, 179, 179));
        labelValorPago.setText("0.00");

        labelDesconto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelDesconto.setForeground(new java.awt.Color(179, 179, 179));
        labelDesconto.setText("0.00");

        labelTroco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTroco.setForeground(new java.awt.Color(179, 179, 179));
        labelTroco.setText("0.00");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(16, 167, 49));
        jLabel3.setText("Valor total do item:");

        labelSubtotalItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelSubtotalItem.setForeground(new java.awt.Color(16, 167, 49));
        labelSubtotalItem.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelNomeDoProduto)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelSubtotalItem)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEstoqueQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPrecoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(labelTotalVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelValorPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelNomeDoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelEstoqueQuantidade))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(labelPrecoProduto))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelSubtotalItem))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelTotalVenda))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelValorPago))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelDesconto))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelTroco))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(textoBuscarProdutoPeloID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBoard1Layout.createSequentialGroup()
                                .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                            .addComponent(textoDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoValorPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCirculo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panelCirculo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addComponent(textoBuscarProdutoPeloID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogVendaLayout = new javax.swing.GroupLayout(dialogVenda.getContentPane());
        dialogVenda.getContentPane().setLayout(dialogVendaLayout);
        dialogVendaLayout.setHorizontalGroup(
            dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
            .addGroup(dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dialogVendaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        dialogVendaLayout.setVerticalGroup(
            dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
            .addGroup(dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dialogVendaLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelBoard2.setBackground(new java.awt.Color(0, 35, 25));
        panelBoard2.setCor1(new java.awt.Color(0, 70, 50));
        panelBoard2.setCor2(new java.awt.Color(26, 122, 102));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Checkout");

        jPanel4.setBackground(new java.awt.Color(45, 45, 45));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 50, 0));

        botaoCarrinhoRemover.setText("Remover");
        botaoCarrinhoRemover.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jPanel4.add(botaoCarrinhoRemover);

        botaoCarrinhoLimpar.setText("Limpar");
        botaoCarrinhoLimpar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jPanel4.add(botaoCarrinhoLimpar);

        jScrollPane4.setBackground(new java.awt.Color(0, 35, 25));
        jScrollPane4.setOpaque(true);

        tabelaCheckout.setBackground(new java.awt.Color(0, 35, 25));
        tabelaCheckout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tabelaCheckout.setGridColor(new java.awt.Color(179, 179, 179));
        tabelaCheckout.setOpaque(false);
        jScrollPane4.setViewportView(tabelaCheckout);

        javax.swing.GroupLayout panelBoard2Layout = new javax.swing.GroupLayout(panelBoard2);
        panelBoard2.setLayout(panelBoard2Layout);
        panelBoard2Layout.setHorizontalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        panelBoard2Layout.setVerticalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabela1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane3.setViewportView(tabela1);

        setBackground(new java.awt.Color(45, 45, 45));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Vendas");

        jPanel1.setBackground(new java.awt.Color(45, 45, 45));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 8, 0));

        botaoAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.setName("adicionar"); // NOI18N
        botaoAdicionar.addActionListener(this::botaoAdicionarActionPerformed);
        jPanel1.add(botaoAdicionar);

        botaoAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.setName("atualizar"); // NOI18N
        botaoAtualizar.addActionListener(this::botaoAtualizarActionPerformed);
        jPanel1.add(botaoAtualizar);

        botaoRemover.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoRemover.setForeground(new java.awt.Color(255, 255, 255));
        botaoRemover.setText("Remover");
        botaoRemover.setName("remover"); // NOI18N
        botaoRemover.addActionListener(this::botaoRemoverActionPerformed);
        jPanel1.add(botaoRemover);

        botaoPermissoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoPermissoes.setForeground(new java.awt.Color(255, 255, 255));
        botaoPermissoes.setText("Detalhes");
        botaoPermissoes.setActionCommand("permissoes");
        botaoPermissoes.setName("permissoes"); // NOI18N
        jPanel1.add(botaoPermissoes);
        botaoPermissoes.getAccessibleContext().setAccessibleName("");

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

        labelDataInicial.setBackground(new java.awt.Color(45, 45, 45));
        labelDataInicial.setForeground(new java.awt.Color(255, 255, 255));
        labelDataInicial.setText("Data inicial:");

        dataInicial.setBackground(new java.awt.Color(45, 45, 45));
        dataInicial.setForeground(new java.awt.Color(255, 255, 255));

        labelDataFinal.setBackground(new java.awt.Color(45, 45, 45));
        labelDataFinal.setForeground(new java.awt.Color(255, 255, 255));
        labelDataFinal.setText("Data final:");

        dataFinal.setBackground(new java.awt.Color(45, 45, 45));
        dataFinal.setForeground(new java.awt.Color(255, 255, 255));

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

    private void comboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private javax.swing.JButton botaoAdicionar;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoAdicionarCarrinho;
    private javax.swing.JButton botaoAtualizar;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoCarrinhoLimpar;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoCarrinhoRemover;
    private javax.swing.JButton botaoImprimir;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoLimpar;
    private javax.swing.JButton botaoPermissoes;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoRemover;
    private com.jotadev.gestao.vendas.visual.componentes.Botao botaoVender;
    private com.jotadev.gestao.vendas.visual.componentes.ComboBox comboBoxCategoria;
    private com.jotadev.gestao.vendas.visual.componentes.ComboBox comboBoxProduto;
    private com.toedter.calendar.JDateChooser dataFinal;
    private com.toedter.calendar.JDateChooser dataInicial;
    private javax.swing.JDialog dialogVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelCarrinho;
    private javax.swing.JLabel labelDataFinal;
    private javax.swing.JLabel labelDataInicial;
    private javax.swing.JLabel labelDesconto;
    private javax.swing.JLabel labelEstoqueQuantidade;
    private javax.swing.JLabel labelNomeDoProduto;
    private javax.swing.JLabel labelPrecoProduto;
    private javax.swing.JLabel labelSubtotalItem;
    public javax.swing.JLabel labelTotalVenda;
    private javax.swing.JLabel labelTroco;
    private javax.swing.JLabel labelValorPago;
    private com.jotadev.gestao.vendas.visual.componentes.PanelBoard panelBoard1;
    private com.jotadev.gestao.vendas.visual.componentes.PanelBoard panelBoard2;
    private com.jotadev.gestao.vendas.visual.componentes.PanelCirculo panelCirculo1;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabela1;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaCheckout;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaVendas;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoBuscarProdutoPeloID;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoCPF;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoDesconto;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoQuantidade;
    private com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto textoValorPago;
    // End of variables declaration//GEN-END:variables
}
