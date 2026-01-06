package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.Produto;
import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.servico.CategoriaServico;
import com.jotadev.gestao.vendas.modelo.servico.ProdutoServico;
import com.jotadev.gestao.vendas.modelo.servico.UsuarioServico;
import com.jotadev.gestao.vendas.modelo.servico.VendaServico;
import com.jotadev.gestao.vendas.visual.formulario.FormularioVenda;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloVenda;
import com.jotadev.gestao.vendas.visual.componentes.Mensagem;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloCheckout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FormularioVendaController implements ActionListener {
    
    private FormularioVenda formularioVenda;
    private VendaServico vendaServico;
    private UsuarioServico usuarioServico;
    private CategoriaServico categoriaServico;
    private TabelaModeloVenda tabelaModeloVenda;
    private TabelaModeloCheckout tabelaModeloCheckout;
    private ProdutoServico produtoServico;

    public FormularioVendaController(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
        this.vendaServico = new VendaServico();
        usuarioServico = new UsuarioServico();
        categoriaServico = new CategoriaServico();
        produtoServico = new ProdutoServico();
        
        this.tabelaModeloVenda = new TabelaModeloVenda(new ArrayList<>());
        this.tabelaModeloCheckout = new TabelaModeloCheckout(new ArrayList<>());
        
        this.formularioVenda.getTabelaVendas().setModel(tabelaModeloVenda);
        this.formularioVenda.getTabelaCheckout().setModel(tabelaModeloCheckout);
        
        atualizarTabelaVenda();
    }
    
    private void atualizarTabelaVenda() {
        // se o usuário possui permissão de adm
        Optional<Usuario> usuario = usuarioServico.buscarPeloId(formularioVenda.getUsuarioId());
        List<VendaDto> lista = new ArrayList<>();
        
        if(usuario.get().getPerfil().equalsIgnoreCase("ADMIN")) {
            lista =  vendaServico.buscarTodos();
        } else {
            
        }
        
        tabelaModeloVenda = new TabelaModeloVenda(lista);
        formularioVenda.getTabelaVendas().setModel(tabelaModeloVenda);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> { mostrarTelaDeVenda(); break; }
        }
    }
    
    private void mostrarTelaDeVenda() {
        for(ActionListener al : formularioVenda.getTextoBuscarProdutoPeloID().getActionListeners()) {
        formularioVenda.getTextoBuscarProdutoPeloID().removeActionListener(al);
        }
        for(ActionListener al : formularioVenda.getBotaoAdicionarCarrinho().getActionListeners()) {
            formularioVenda.getBotaoAdicionarCarrinho().removeActionListener(al);
        }

        formularioVenda.getTextoBuscarProdutoPeloID().addActionListener(e -> {
            System.out.println("Evento disparado! Buscando ID: " + formularioVenda.getTextoBuscarProdutoPeloID().getText()); // Teste de console
            buscarProdutoPeloId();
        });
        
        formularioVenda.getBotaoAdicionarCarrinho().addActionListener(e -> {
            System.out.println("Clique detectado no botão adicionar!"); //teste
            adicionarItem();
        });
        
        formularioVenda.getBotaoCarrinhoRemover().addActionListener(e -> {
            //pega o índice da linha selecionada
            int linhaSelecionada = formularioVenda.getTabelaCheckout().getSelectedRow();
            
            if (linhaSelecionada >= 0) {
                // remove linha
                tabelaModeloCheckout.remover(linhaSelecionada);
                //atualiza o contador do carrinho
                int totalRestante = tabelaModeloCheckout.getRowCount();
                formularioVenda.getLabelCarrinho().setText(String.valueOf(totalRestante));
                
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, "Item removido com sucesso!");
            } else {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Por favor, selecione um item na tabela para remover!");
            }
        });
        
        formularioVenda.getTextoQuantidade().addKeyListener(new KeyAdapter(){
           @Override
           public void keyReleased(KeyEvent evt) {
               calcularTotal();
           }
        });

        formularioVenda.getDialogVenda().pack();
        formularioVenda.getDialogVenda().setLocationRelativeTo(null);
        formularioVenda.getDialogVenda().setVisible(true);
        preencherComboBoxCategoria();
    }
    
    private void preencherComboBoxCategoria() {
        formularioVenda.getComboBoxCategoria().removeAllItems();
        formularioVenda.getComboBoxProduto().removeAllItems();
        
        formularioVenda.getComboBoxCategoria().addItem("Selecione a categoria");
        formularioVenda.getComboBoxProduto().addItem("Selecione o produto");
        
        categoriaServico.buscarTodos()
                .stream()
                .forEach(c -> formularioVenda.getComboBoxCategoria().addItem(c.getNome()));
    }
    
    private void buscarProdutoPeloId() {
        String idTexto = formularioVenda.getTextoBuscarProdutoPeloID().getText().trim();
        
        if (idTexto.isEmpty()) return;
        
        try {
            Long id = Long.parseLong(idTexto);
            Optional<Produto> produtoOpt = produtoServico.buscarPeloId(id);
            
            if (produtoOpt.isPresent()) {
                Produto p = produtoOpt.get(); // extrai o produto de dentro do optional
                
                formularioVenda.getLabelNomeDoProduto().setText(p.getNome());
                formularioVenda.getLabelPrecoProduto().setText(p.getPreco().toString());
                formularioVenda.getLabelEstoqueQuantidade().setText(String.valueOf(p.getQuantidade()));
                formularioVenda.getTextoQuantidade().requestFocus();
            } else {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Produto não encontrado!");
            }
        } catch (NumberFormatException e) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Digite um ID numérico válido!");
        }
    }
    
    private void calcularTotal() {
        try {
            String precoTexto = formularioVenda.getLabelPrecoProduto().getText()
                                    .replace("R$", "")
                                    .replace(",", ".")
                                    .trim();
            
            double preco = Double.parseDouble(precoTexto);
            
            // pega a quantidade
            String qtdTexto = formularioVenda.getTextoQuantidade().getText().trim();
            
            if (!qtdTexto.isBlank()) {
                int quantidade = Integer.parseInt(qtdTexto);
                double total = preco * quantidade;
                
                // atualiza o label do Preço Total
                formularioVenda.getLabelTotalVenda().setText(String.format("%.2f", total));
            } else {
                formularioVenda.getLabelTotalVenda().setText("0.00");
            }
        } catch (Exception e) {
            formularioVenda.getLabelTotalVenda().setText("0.00");
        }
    }
    
    private void adicionarItem() {
        System.out.println("botão adicionar clicado!"); //teste
        try {
            String nome = formularioVenda.getLabelNomeDoProduto().getText();
            if (nome.isEmpty() || nome.equals("Nome do produto")) {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Busque um produto primeiro!");
                return;
            }
            
            String qtdTexto = formularioVenda.getTextoQuantidade().getText().trim();
            if (qtdTexto.isEmpty() || Integer.parseInt(qtdTexto) <= 0) {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Informe uma quantidade válida!");
                return;
            }
            
            int quantidade = Integer.parseInt(qtdTexto);
            double preco = Double.parseDouble(formularioVenda.getLabelPrecoProduto().getText().replace(",", "."));
            double subtotal = preco * quantidade;
            
            VendaDto item = VendaDto.builder()
                    .id(Long.parseLong(formularioVenda.getTextoBuscarProdutoPeloID().getText()))
                    .nome(nome)
                    .preco(preco)
                    .quantidade(quantidade)
                    .subtotal(subtotal)
                    .build();
            
            tabelaModeloCheckout.adicionar(item);
            int totalItens = tabelaModeloCheckout.getRowCount();
            formularioVenda.getLabelCarrinho().setText(String.valueOf(totalItens));
            System.out.println("Item adicionado ao modelo. Total de linhas agora: " + tabelaModeloCheckout.getRowCount()); //teste
            limparCamposProduto();
            
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, "Produto adicionado!");
            
        } catch (Exception e) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Erro ao adicionar item!");
        }
        
    }
    
    private void limparCamposProduto() {
        formularioVenda.getTextoBuscarProdutoPeloID().setText("");
        formularioVenda.getLabelNomeDoProduto().setText("Nome do produto");
        formularioVenda.getLabelPrecoProduto().setText("0.00");
        formularioVenda.getLabelEstoqueQuantidade().setText("0");
        formularioVenda.getTextoQuantidade().setText("");
        formularioVenda.getLabelTotalVenda().setText("0.00");
        
        formularioVenda.getTextoBuscarProdutoPeloID().requestFocus();
    }
}
