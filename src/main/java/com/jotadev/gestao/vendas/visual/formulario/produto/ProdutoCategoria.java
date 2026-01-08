package com.jotadev.gestao.vendas.visual.formulario.produto;

import com.jotadev.gestao.vendas.modelo.entidade.Categoria;
import com.jotadev.gestao.vendas.modelo.servico.CategoriaServico;
import com.jotadev.gestao.vendas.visual.componentes.Botao;
import com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto;
import com.jotadev.gestao.vendas.visual.componentes.ComboBox;
import com.jotadev.gestao.vendas.visual.componentes.Mensagem;
import com.jotadev.gestao.vendas.visual.componentes.ModernScrollBarUI;
import com.jotadev.gestao.vendas.visual.componentes.Tabela;
import com.jotadev.gestao.vendas.visual.formulario.FormularioProduto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class ProdutoCategoria extends javax.swing.JPanel {
    
    private CampoDeTexto textoNomeCategoria;
    private CampoDeTexto textoDescricaoCategoria;
    private JButton botaoResetCategoria;
    private Botao botaoCategoria;
    
    private CampoDeTexto textoNomeProduto;
    private CampoDeTexto textoDescricaoProduto;
    private CampoDeTexto textoPrecoProduto;
    private ComboBox comboBoxCategoriaProduto;
    private Botao botaoProduto;
    
    String caminho = "\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\";
    
//    private FormularioProdutoController formularioProdutoController;
    private FormularioProduto formularioProduto;
    private CategoriaServico categoriaServico;

    public ProdutoCategoria(FormularioProduto formularioProduto) {
        initComponents();
        
        produto.setVisible(true);
        categoria.setVisible(false);
        
        formularioProduto();
        formularioCategoria();
        this.formularioProduto = formularioProduto;
        
        categoriaServico = new CategoriaServico();
        preencherComboBoxCategoria();
        
        this.setBackground(new Color(45, 45, 45));
        this.background.setBackground(new Color(45, 45, 45));
        
        eventoNaTabela();
        limpar();
    }
    
    public void preencherComboBoxCategoria() {
        comboBoxCategoriaProduto.removeAllItems();
        comboBoxCategoriaProduto.addItem("Selecione a categoria.");
        
        categoriaServico.buscarTodos()
                .forEach(c -> comboBoxCategoriaProduto.addItem(c.getNome()));
    }
    
    private void eventoNaTabela() {
        tabelaCategoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabelaCategoria.getSelectedRow();
                int coluna = tabelaCategoria.getSelectedColumn();
                Categoria categoria = formularioProduto.getFormularioProdutoController().getTabelaModeloCategoria().getCategorias().get(linha);
                textoNomeCategoria.setText(categoria.getNome());
                textoDescricaoCategoria.setText(categoria.getDescricao());
                formularioProduto.getFormularioProdutoController().setCategoriaId(categoria.getId());
                
                if (coluna == 3) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover\n"
                            + categoria.getNome() + "?", "Remover categoria.", JOptionPane.YES_NO_OPTION);
                    
                    if (confirmar == JOptionPane.YES_OPTION) {
                        categoriaServico.removerPeloId(categoria.getId());
                        formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, "Removido com Sucesso.");
                        formularioProduto.getFormularioProdutoController().atualizarTabelaCategoria();
                        formularioProduto.getFormularioProdutoController().limparCamposCategoria();
                        preencherComboBoxCategoria();
                    }
                }
            }
        });
    }
    
    private void limpar() {
        botaoResetCategoria.addActionListener(e -> {
        formularioProduto.getFormularioProdutoController().limparCamposCategoria();
        });
    }
    
    private void formularioProduto(){
        produto.setLayout(new MigLayout("insets 0, wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        
        Color verdeAguaEscuro = new Color(28, 166, 154);
        Color verdePetroleoEscuro = new Color(10, 90, 80);
                
        
        JLabel label = new JLabel("Registro de Produto");
        label.setForeground(verdeAguaEscuro);
        label.setFont(new Font("sansserif", 1, 20));
        produto.add(label);
        
        textoNomeProduto = new CampoDeTexto();
        textoNomeProduto.setDicas("Nome");
        textoNomeProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "produto1.png"));
        produto.add(textoNomeProduto, "w 60%");
        
        textoDescricaoProduto = new CampoDeTexto();
        textoDescricaoProduto.setDicas("Descrição");
        textoDescricaoProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "descricao.png"));
        produto.add(textoDescricaoProduto, "w 60%");
        
        textoPrecoProduto = new CampoDeTexto();
        textoPrecoProduto.setDicas("Preço");
        textoPrecoProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "preco.png"));
        produto.add(textoPrecoProduto, "w 60%");
        
        comboBoxCategoriaProduto = new ComboBox();
        comboBoxCategoriaProduto.addItem("Selecione a categoria.");
        comboBoxCategoriaProduto.setBackground(new Color(0,35,25));
        produto.add(comboBoxCategoriaProduto, "w 60%, h 35");
        
        botaoProduto = new Botao();
        botaoProduto.setBackground(verdePetroleoEscuro);
        botaoProduto.setForeground(Color.WHITE);
        botaoProduto.setFont(new Font("sansserif", 5, 14));
        botaoProduto.setActionCommand("salvarproduto");
        botaoProduto.setText("Salvar");
        produto.add(botaoProduto, "w 40%, h 40");
    }
    
    private void formularioCategoria() {
        categoria.setLayout(new MigLayout("insets 0, wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        
        Color verdeAguaEscuro = new Color(28, 166, 154);
        Color verdePetroleoEscuro = new Color(10, 90, 80);
        
        JLabel label = new JLabel("Registro de Categoria.");
        label.setForeground(verdeAguaEscuro);
        label.setFont(new Font("sansserif", 1, 20));
        categoria.add(label);
        
        textoNomeCategoria = new CampoDeTexto();
        textoNomeCategoria.setDicas("Nome");
        textoNomeCategoria.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "produto1.png"));
        categoria.add(textoNomeCategoria, "w 60%");
        
        textoDescricaoCategoria = new CampoDeTexto();
        textoDescricaoCategoria.setDicas("Descrição");
        textoDescricaoCategoria.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir") + caminho + "descricao.png"));
        categoria.add(textoDescricaoCategoria, "w 60%");
        
        botaoResetCategoria = new JButton("Limpar");
        botaoResetCategoria.setContentAreaFilled(false);
        botaoResetCategoria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoResetCategoria.setForeground(verdeAguaEscuro);
        categoria.add(botaoResetCategoria);
        
        botaoCategoria = new Botao();
        botaoCategoria.setBackground(verdePetroleoEscuro);
        botaoCategoria.setForeground(Color.WHITE);
        botaoCategoria.setFont(new Font("sansserif", 5, 14));
        botaoCategoria.setActionCommand("salvarcategoria");
        botaoCategoria.setText("Salvar");
        categoria.add(botaoCategoria, "w 40%, h 40");
        
        jScrollPane1.getViewport().setBackground(new Color(45, 45, 45));
        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportBorder(null);
        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(8, Integer.MAX_VALUE));

        jScrollPane1.getHorizontalScrollBar().setUI(new ModernScrollBarUI());
        jScrollPane1.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, 8));

        categoria.add(jScrollPane1, "w 80%, h 40%");
    }
        
    
    public void mostrarProduto(boolean mostrar) {
        if (mostrar) {
            produto.setVisible(true);
            categoria.setVisible(false);
        } else { 
            produto.setVisible(false);
            categoria.setVisible(true);
        }
    }

    public CampoDeTexto getTextoNomeCategoria() {
        return textoNomeCategoria;
    }

    public CampoDeTexto getTextoDescricaoCategoria() {
        return textoDescricaoCategoria;
    }

    public JButton getBotaoResetCategoria() {
        return botaoResetCategoria;
    }

    public Botao getBotaoCategoria() {
        return botaoCategoria;
    }

    public CampoDeTexto getTextoNomeProduto() {
        return textoNomeProduto;
    }

    public CampoDeTexto getTextoDescricaoProduto() {
        return textoDescricaoProduto;
    }

    public CampoDeTexto getTextoPrecoProduto() {
        return textoPrecoProduto;
    }
    

    public ComboBox getComboBoxCategoriaProduto() {
        return comboBoxCategoriaProduto;
    }

    public Botao getBotaoProduto() {
        return botaoProduto;
    }

    public Tabela getTabelaCategoria() {
        return tabelaCategoria;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        produto = new javax.swing.JPanel();
        categoria = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCategoria = new com.jotadev.gestao.vendas.visual.componentes.Tabela();

        setBackground(new java.awt.Color(45, 45, 45));

        background.setBackground(new java.awt.Color(45, 45, 45));
        background.setLayout(new java.awt.CardLayout());

        produto.setBackground(new java.awt.Color(45, 45, 45));
        produto.setLayout(new java.awt.BorderLayout());
        background.add(produto, "card3");

        categoria.setBackground(new java.awt.Color(45, 45, 45));
        categoria.setLayout(new java.awt.BorderLayout());

        tabelaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaCategoria);

        categoria.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        background.add(categoria, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel categoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel produto;
    private com.jotadev.gestao.vendas.visual.componentes.Tabela tabelaCategoria;
    // End of variables declaration//GEN-END:variables
}
