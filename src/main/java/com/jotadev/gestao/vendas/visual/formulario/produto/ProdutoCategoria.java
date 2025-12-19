package com.jotadev.gestao.vendas.visual.formulario.produto;

import com.jotadev.gestao.vendas.visual.componentes.Botao;
import com.jotadev.gestao.vendas.visual.componentes.CampoDeTexto;
import com.jotadev.gestao.vendas.visual.componentes.ComboBox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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

    public ProdutoCategoria() {
        initComponents();
        
        produto.setVisible(true);
        categoria.setVisible(false);
        
        formularioProduto();
        formularioCategoria();
    }
    
    private void formularioProduto(){
        produto.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        
        JLabel label = new JLabel("Registro de Produto");
        label.setForeground(Color.decode("#1cb5e0"));
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
        produto.add(textoNomeProduto, "w 60%, h 35");
        
        botaoProduto = new Botao();
        botaoProduto.setBackground(new Color(28, 181, 224));
        botaoProduto.setForeground(Color.WHITE);
        botaoProduto.setFont(new Font("sansserif", 5, 14));
        botaoProduto.setActionCommand("salvarproduto");
        botaoProduto.setText("Salvar");
        produto.add(botaoProduto, "w 40%, h 40");
    }
    
    private void formularioCategoria() {
        categoria.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        
        JLabel label = new JLabel("Registro de Categoria.");
        label.setForeground(Color.decode("#1cb5e0"));
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
        botaoResetCategoria.setForeground(Color.decode("#1cb5e0"));
        categoria.add(botaoResetCategoria);
        
        botaoCategoria = new Botao();
        botaoCategoria.setBackground(new Color(28, 181, 224));
        botaoCategoria.setForeground(Color.WHITE);
        botaoCategoria.setFont(new Font("sansserif", 5, 14));
        botaoCategoria.setActionCommand("salvarcategoria");
        botaoCategoria.setText("Salvar");
        categoria.add(botaoCategoria, "w 40%, h 40");
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        produto = new javax.swing.JPanel();
        categoria = new javax.swing.JPanel();

        background.setLayout(new java.awt.CardLayout());

        produto.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout produtoLayout = new javax.swing.GroupLayout(produto);
        produto.setLayout(produtoLayout);
        produtoLayout.setHorizontalGroup(
            produtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        produtoLayout.setVerticalGroup(
            produtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        background.add(produto, "card3");

        categoria.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout categoriaLayout = new javax.swing.GroupLayout(categoria);
        categoria.setLayout(categoriaLayout);
        categoriaLayout.setHorizontalGroup(
            categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        categoriaLayout.setVerticalGroup(
            categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

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
    private javax.swing.JPanel produto;
    // End of variables declaration//GEN-END:variables
}
