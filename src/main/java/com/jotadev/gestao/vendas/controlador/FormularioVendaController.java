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
import javax.swing.JOptionPane;

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
        
        formularioVenda.getTextoValorPago().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                calcularTroco();
            }

        });

        formularioVenda.getDialogVenda().pack();
        formularioVenda.getDialogVenda().setLocationRelativeTo(null);
        formularioVenda.getDialogVenda().setVisible(true);
        preencherComboBoxCategoria();
    }
    
    private void calcularTroco() {
        try {
            String totalVendaTexto = formularioVenda.getLabelTotalVenda().getText().replace(",", ".");
            BigDecimal totalVenda = new BigDecimal(totalVendaTexto);
                    
            String pagoTexto = formularioVenda.getTextoValorPago().getText().replace(",", ".");
            if (pagoTexto.isEmpty()) return;
                    
            BigDecimal valorPago = new BigDecimal(pagoTexto);
                    
            BigDecimal troco = valorPago.subtract(totalVenda);
                    
            if (troco.compareTo(BigDecimal.ZERO) >= 0) {
                formularioVenda.getLabelTroco().setText(String.format("%.2f", troco));
            } else {
                formularioVenda.getLabelTroco().setText("0.00");
            }
        } catch (Exception e) {
            formularioVenda.getLabelTroco().setText("0.00");
        }
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
    System.out.println("DEBUG: Tentando adicionar item ao carrinho...");
    try {
        String nome = formularioVenda.getLabelNomeDoProduto().getText();
        String idTexto = formularioVenda.getTextoBuscarProdutoPeloID().getText();

        if (nome.isEmpty() || nome.equals("Nome do produto") || idTexto.isEmpty()) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Busque um produto primeiro!");
            return;
        }

        //SINCRONIZAÇÃO: Busca o ID que está na tela
        Long idRecuperado = Long.parseLong(idTexto);
        Optional<Produto> produtoAtualizado = produtoServico.buscarPeloId(idRecuperado);

        if (produtoAtualizado.isPresent()) {
            Produto p = produtoAtualizado.get();
            int estoqueReal = p.getQuantidade();
            
            String qtdDigitadaStr = formularioVenda.getTextoQuantidade().getText().trim();
            int qtdDesejada = qtdDigitadaStr.isEmpty() ? 0 : Integer.parseInt(qtdDigitadaStr);

            //VALIDAÇÃO DE ESTOQUE REAL
            if (qtdDesejada <= 0) {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Quantidade deve ser maior que zero!");
                return;
            }

            if (qtdDesejada > estoqueReal) {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Estoque insuficiente! Disponível: " + estoqueReal);
                return;
            }

            //CRIAÇÃO DO ITEM PARA O CARRINHO
            VendaDto item = VendaDto.builder()
                    .id(p.getId())
                    .nome(p.getNome())
                    .preco(p.getPreco().doubleValue()) 
                    .quantidade(qtdDesejada)
                    .subtotal(p.getPreco().doubleValue() * qtdDesejada)
                    .build();

            //ADIÇÃO E ATUALIZAÇÃO DA UI
            tabelaModeloCheckout.adicionar(item);
            
            int totalItens = tabelaModeloCheckout.getRowCount();
            formularioVenda.getLabelCarrinho().setText(String.valueOf(totalItens));
            
            atualizarTotalVenda();
            limparCamposProduto();
            
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, "Produto adicionado!");
        } else {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Produto não encontrado no banco!");
        }

    } catch (NumberFormatException e) {
        formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Quantidade inválida!");
    } catch (Exception e) {
        e.printStackTrace();
        formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Erro ao adicionar item!");
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
        // Validação de Carrinho Vazio
        if (tabelaModeloCheckout.getRowCount() == 0) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "O carrinho está vazio!");
            return;
        }

        try {
            // Captura e Limpeza de Dados da Tela
            String totalTexto = formularioVenda.getLabelTotalVenda().getText()
                    .replace("R$", "").replace(".", "").replace(",", ".").trim();
            String pagoTexto = formularioVenda.getTextoValorPago().getText().replace(",", ".");
            String descontoTexto = formularioVenda.getTextoDesconto().getText().replace(",", ".");

            BigDecimal totalVenda = new BigDecimal(totalTexto.isEmpty() ? "0" : totalTexto);
            BigDecimal valorPago = new BigDecimal(pagoTexto.isEmpty() ? "0" : pagoTexto);
            BigDecimal desconto = new BigDecimal(descontoTexto.isEmpty() ? "0" : descontoTexto);

            // Validação de Pagamento Insuficiente (Considerando Desconto)
            BigDecimal valorComDesconto = totalVenda.subtract(desconto);

            if (valorPago.compareTo(valorComDesconto) < 0) {
                JOptionPane.showMessageDialog(null,
                        "Valor pago insuficiente! O total com desconto é R$ " + valorComDesconto,
                        "Pagamento insuficiente",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Cálculo do Troco e Configuração do Objeto Venda
            BigDecimal troco = valorPago.subtract(valorComDesconto);

            Venda venda = new Venda();
            venda.setTotalVenda(totalVenda);
            venda.setValorPago(valorPago);
            venda.setDesconto(desconto);
            venda.setTroco(troco);
            venda.setDataCriacao(LocalDateTime.now());

            // Usuário e Cliente
            Long idUsuario = formularioVenda.getUsuarioId();
            venda.setUsuarioId(idUsuario == null ? 1L : idUsuario);
            venda.setClienteId(1L);

            // Persistência no Banco de Dados
            String mensagem = vendaServico.salvar(venda, tabelaModeloCheckout.getItens());

            if (mensagem.startsWith("Venda realizada")) {
                // Atualização Automática de Estoque
                atualizarEstoquePosVenda();

                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);

                // Sincronização da UI
                atualizarTabelaVenda(); 
                limparTudoAposVenda();
            } else {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
            }

        } catch (NumberFormatException e) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Erro nos valores digitados!");
        } catch (Exception ex) {
            ex.printStackTrace();
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Erro inesperado: " + ex.getMessage());
        }
    }

    // Método auxiliar para deixar o código principal mais limpo
    private void atualizarEstoquePosVenda() {
        for (int i = 0; i < tabelaModeloCheckout.getRowCount(); i++) {
            Long produtoId = Long.valueOf(tabelaModeloCheckout.getValueAt(i, 0).toString());
            Integer qtdVendida = (Integer) tabelaModeloCheckout.getValueAt(i, 2);

            produtoServico.buscarPeloId(produtoId).ifPresent(p -> {
                int novoEstoque = p.getQuantidade() - qtdVendida;
                produtoServico.atualizarEstoque(produtoId, novoEstoque);
            });
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
