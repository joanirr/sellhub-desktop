package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.Produto;
import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.entidade.Venda;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        // Busca a lista atualizada do banco
        List<VendaDto> lista = vendaServico.buscarTodos(); 

        // Cria um novo modelo com os dados novos
        tabelaModeloVenda = new TabelaModeloVenda(lista);

        // Seta o modelo na tabela da View
        formularioVenda.getTabelaVendas().setModel(tabelaModeloVenda);

        // Força a interface a se redesenhar
        formularioVenda.getTabelaVendas().revalidate();
        formularioVenda.getTabelaVendas().repaint();
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
        for(ActionListener al : formularioVenda.getComboBoxCategoria().getActionListeners()) {
            formularioVenda.getComboBoxCategoria().removeActionListener(al);
        }
        for(ActionListener al : formularioVenda.getBotaoCarrinhoRemover().getActionListeners()) {
            formularioVenda.getBotaoCarrinhoRemover().removeActionListener(al);
        }
        
        for(ActionListener al : formularioVenda.getBotaoVender().getActionListeners()) {
            formularioVenda.getBotaoVender().removeActionListener(al);
        }

        formularioVenda.getTextoBuscarProdutoPeloID().addActionListener(e -> {
            System.out.println("Evento disparado! Buscando ID: " + formularioVenda.getTextoBuscarProdutoPeloID().getText()); // Teste de console
            buscarProdutoPeloId();
        });
        
        formularioVenda.getBotaoAdicionarCarrinho().addActionListener(e -> {
            System.out.println("Clique detectado no botão adicionar!"); //teste
            adicionarItem();
        });
        
        formularioVenda.getComboBoxCategoria().addActionListener(e -> {
            String categoriaSelecionada = (String) formularioVenda.getComboBoxCategoria().getSelectedItem();
            System.out.println("Categoria selecionada: " + categoriaSelecionada); // Log para teste
            atualizarProdutosPorCategoria(categoriaSelecionada);
        });
        
        formularioVenda.getComboBoxProduto().addActionListener(e -> {
            String produtoNome = (String) formularioVenda.getComboBoxProduto().getSelectedItem();
            preencherCamposPeloNome(produtoNome);
        });
        
        formularioVenda.getBotaoVender().addActionListener(e -> {
            finalizarVenda();
        });
        
        formularioVenda.getBotaoCarrinhoRemover().addActionListener(e -> {
            int linhaSelecionada = formularioVenda.getTabelaCheckout().getSelectedRow();

            if (linhaSelecionada >= 0) {
                tabelaModeloCheckout.remover(linhaSelecionada);
                atualizarTotalVenda();

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
                System.out.println("PRODUTO ENCONTRADO: " + p.getNome() + " | QTD NO OBJETO: " + p.getQuantidade());
                
                formularioVenda.getLabelNomeDoProduto().setText(p.getNome());
                formularioVenda.getLabelPrecoProduto().setText(String.valueOf(p.getPreco()));
                formularioVenda.getLabelEstoqueQuantidade().setText(String.valueOf(p.getQuantidade()));
                formularioVenda.getTextoQuantidade().requestFocus();
                
                // Proteção para o estoque não vir "null"
                Integer estoque = p.getQuantidade();
                formularioVenda.getLabelEstoqueQuantidade().setText(estoque != null ? String.valueOf(estoque) : "0");
            } else {
                formularioVenda.getLabelEstoqueQuantidade().setText("0");
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Produto não encontrado!");
            }
        } catch (NumberFormatException e) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Digite um ID numérico válido!");
        }
    }
    
    private void calcularTotal() {
        try {
            String precoTexto = formularioVenda.getLabelPrecoProduto().getText().replace(",", ".");
            double precoUnitario = Double.parseDouble(precoTexto);
            
            // pega a quantidade
            String qtdTexto = formularioVenda.getTextoQuantidade().getText().trim();
            
            if (!qtdTexto.isBlank()) {
                int qtd = Integer.parseInt(qtdTexto);
                double subtotalCalculado = precoUnitario * qtd;
                
                // atualiza o label do preço total do item
                formularioVenda.getLabelSubtotalItem().setText(String.format("%.2f", subtotalCalculado));
            }
        } catch (Exception e) {
            formularioVenda.getLabelSubtotalItem().setText("0.00");
        }
    }
    
    public void atualizarTotalVenda() {
        try {
            double totalGeral = 0.0;
            for (int i = 0; i < tabelaModeloCheckout.getRowCount(); i++) {
                // Acessar a coluna 4 (Subtotal)
                Object valorLinha = tabelaModeloCheckout.getValueAt(i, 4);
                if (valorLinha != null) {
                    // Remove possíveis "R$" ou espaços que bugam o Double.parseDouble
                    String limparValor = valorLinha.toString().replace("R$", "").replace(",", ".").trim();
                    totalGeral += Double.parseDouble(limparValor);
                }
            }
            formularioVenda.getLabelTotalVenda().setText(String.format("%.2f", totalGeral));
        } catch (Exception e) {
            System.err.println("Erro no cálculo: " + e.getMessage());
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
            
            String estoqueTexto = formularioVenda.getLabelEstoqueQuantidade().getText();
    
            // Proteção contra "null" ou textos vazios
            if (estoqueTexto == null || estoqueTexto.equals("null") || estoqueTexto.isEmpty()) {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Estoque inválido!");
                return;
            }

            int estoqueAtual = Integer.parseInt(estoqueTexto);
            int qtdDesejada = Integer.parseInt(formularioVenda.getTextoQuantidade().getText().trim());

            if (qtdDesejada > estoqueAtual) {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Estoque insuficiente! Disponível: " + estoqueAtual);
                return;
            }
            
            if (qtdDesejada <= 0) {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Quantidade deve ser maior que zero!");
                return;
            }
            
            String textoPreco = formularioVenda.getLabelPrecoProduto().getText().replace(",", ".");
            double vPreco = Double.parseDouble(textoPreco);

            int vQuantidade = Integer.parseInt(formularioVenda.getTextoQuantidade().getText().trim());

            // Calculamos o subtotal aqui para passar para o DTO
            double vSubtotal = vPreco * vQuantidade;
            
            VendaDto item = VendaDto.builder()
                    .id(Long.parseLong(formularioVenda.getTextoBuscarProdutoPeloID().getText()))
                    .nome(nome)
                    .preco(vPreco)
                    .quantidade(vQuantidade)
                    .subtotal(vSubtotal)
                    .build();
            
            tabelaModeloCheckout.adicionar(item);
            int totalItens = tabelaModeloCheckout.getRowCount();
            formularioVenda.getLabelCarrinho().setText(String.valueOf(totalItens));
            
            atualizarTotalVenda();
            
            limparCamposProduto();
            
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, "Produto adicionado!");
            
        } catch (Exception e) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Erro na contagem do estoque ou quantidade!");
        }
        
    }
    
    private void limparCamposProduto() {
        formularioVenda.getTextoBuscarProdutoPeloID().setText("");
        formularioVenda.getLabelNomeDoProduto().setText("Nome do produto");
        formularioVenda.getLabelPrecoProduto().setText("0.00");
        formularioVenda.getLabelEstoqueQuantidade().setText("0");
        formularioVenda.getLabelSubtotalItem().setText("0.00");
        formularioVenda.getTextoQuantidade().setText("");
        
        formularioVenda.getTextoBuscarProdutoPeloID().requestFocus();
    }
    
    private void atualizarProdutosPorCategoria(String nomeCategoria) {
        formularioVenda.getComboBoxProduto().removeAllItems();
        formularioVenda.getComboBoxProduto().addItem("Selecione o produto");

        if (nomeCategoria == null || nomeCategoria.equals("Selecione a categoria")) return;

        produtoServico.buscarTodos().stream()
                .filter(p -> {
                    Object catObj = p.getCategoria();
                    if (catObj == null) return false;

                    // Se catObj for o objeto Categoria, o toString() ou o cast funcionará
                    String nomeBanco = "";

                    if (catObj instanceof com.jotadev.gestao.vendas.modelo.entidade.Categoria) {
                        nomeBanco = ((com.jotadev.gestao.vendas.modelo.entidade.Categoria) catObj).getNome();
                    } else {
                        // Se o Java estiver tratando como String ou outro objeto
                        nomeBanco = catObj.toString(); 
                    }

                    // Log de emergência para vermos o que o Java "vê"
                    System.out.println("COMPARAÇÃO -> Banco: [" + nomeBanco + "] | Selecionado: [" + nomeCategoria + "]");

                    return nomeBanco != null && nomeBanco.trim().equalsIgnoreCase(nomeCategoria.trim());
                })
                .forEach(p -> formularioVenda.getComboBoxProduto().addItem(p.getNome()));
    }
    
    private void preencherCamposPeloNome(String nome) {
        if (nome == null || nome.equals("Selecione o produto")) return;
        
        //busca o produto na lista geral pelo nome
        Optional<Produto> produtoOpt = produtoServico.buscarPeloNome(nome);
            produtoOpt.ifPresent(p -> {
                formularioVenda.getLabelNomeDoProduto().setText(p.getNome());
                formularioVenda.getLabelPrecoProduto().setText(String.format("%.2f", p.getPreco()));

                Integer estoque = p.getQuantidade();
                formularioVenda.getLabelEstoqueQuantidade().setText(estoque != null ? String.valueOf(estoque) : "0");

                // Preenche o ID para o sistema saber qual produto é na hora de adicionar ao carrinho
                formularioVenda.getTextoBuscarProdutoPeloID().setText(p.getId().toString());
                formularioVenda.getTextoQuantidade().requestFocus();

                System.out.println("Produto carregado pelo Combo: " + p.getNome() + " | Estoque: " + p.getQuantidade());
            });
    }
    
    private void finalizarVenda() {
        if (tabelaModeloCheckout.getRowCount() == 0) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "O carrinho está vazio!");
            return;
        }
        Venda venda = new Venda();
        String totalLimpo = formularioVenda.getLabelTotalVenda().getText()
                .replace("R$", "").replace(".", "").replace(",", ".").trim();
        if (totalLimpo.isEmpty()) totalLimpo = "0.00";
        venda.setTotalVenda(new BigDecimal(totalLimpo));

        try {
            String pagoTexto = formularioVenda.getTextoValorPago().getText().replace(",", ".");
            String descontoTexto = formularioVenda.getTextoDesconto().getText().replace(",", ".");
            
            BigDecimal valorPago = new BigDecimal(pagoTexto.isEmpty() ? "0" : pagoTexto);
            BigDecimal desconto = new BigDecimal(descontoTexto.isEmpty() ? "0" : descontoTexto);
            BigDecimal totalVenda = venda.getTotalVenda();
            
            BigDecimal valorComDesconto = totalVenda.subtract(desconto);
            BigDecimal troco = valorPago.subtract(valorComDesconto);
            
            venda.setValorPago(valorPago);
            venda.setDesconto(desconto);
            venda.setTroco(troco.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : troco);
            venda.setDataCriacao(LocalDateTime.now());
            
            Long idUsuario = formularioVenda.getUsuarioId();
            
            if (idUsuario == null) {
                idUsuario =1L;
            }
            
            venda.setUsuarioId(idUsuario);

            String mensagem = vendaServico.salvar(venda, tabelaModeloCheckout.getItens()); 

            if (mensagem.startsWith("Venda realizada")) {
                for (int i = 0; i < tabelaModeloCheckout.getRowCount(); i++) {
                    Long produtoId = Long.valueOf(tabelaModeloCheckout.getValueAt(i, 0).toString());
                    Integer qtdVendida = Integer.valueOf(tabelaModeloCheckout.getValueAt(i, 2).toString());

                    Optional<Produto> produtoOpt = produtoServico.buscarPeloId(produtoId);
                    if (produtoOpt.isPresent()) {
                        int novoEstoque = produtoOpt.get().getQuantidade() - qtdVendida;
                        produtoServico.atualizarEstoque(produtoId, novoEstoque);
                    }
                }

                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);

                // Sincronização
                atualizarTabelaVenda(); 
                limparTudoAposVenda();
            } else {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Erro ao finalizar: " + ex.getMessage());
        }
    }
    
    private void limparTudoAposVenda() {
        // Esvazia a lista do TableModel do Checkout
        tabelaModeloCheckout.limpar();

        // Atualiza os labels
        formularioVenda.getLabelTotalVenda().setText("0.00");
        formularioVenda.getLabelCarrinho().setText("0");

        // Limpa os campos de busca
        limparCamposProduto();
    }
}
